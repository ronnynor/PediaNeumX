package com.qnecesitas.pedianeumx.ui.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.qnecesitas.pedianeumx.ui.permissions.Permissions.Companion.TAG
import com.qnecesitas.pedianeumx.ui.permissions.Permissions.Companion.checkIfPermissionGranted
import com.qnecesitas.pedianeumx.ui.permissions.Permissions.Companion.shouldShowPermissionRationale
import kotlin.collections.all
import kotlin.collections.forEach
import com.qnecesitas.pedianeumx.R
import kotlin.collections.joinToString
import kotlin.jvm.java

class Permissions {
    companion object {
        val TAG: String = Permissions::class.java.simpleName

        fun checkIfPermissionGranted(context: Context, permission: String): Boolean {
            return (ContextCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED)
        }

        fun shouldShowPermissionRationale(context: Context, permission: String): Boolean {

            val activity = context as Activity?
            if (activity == null) {
                Log.d(TAG, "Activity is null")
            }

            return ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                permission
            )
        }
    }
}

@Composable
fun PermissionsUI(
    context: Context,
    permissions: Array<String>,
    permissionRationale: String,
    snackbarHostState: SnackbarHostState,
    permissionAction: (PermissionAction) -> Unit
) {
    var permissionGranted = true

    permissions.forEach { permission ->
        if (!checkIfPermissionGranted(
                context,
                permission
            )
        ) {
            permissionGranted = false
        }
    }


    if (permissionGranted) {
        Log.d(TAG, "Permission already granted, exiting..")
        permissionAction(PermissionAction.OnPermissionGranted)
        return
    }


    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        if (permissions.all({ it.value })) {
            Log.d(TAG, "Permission provided by user")
            // Permission Accepted
            permissionAction(PermissionAction.OnPermissionGranted)
        } else {
            Log.d(TAG, "Permission denied by user")
            // Permission Denied
            permissionAction(PermissionAction.OnPermissionDenied)
        }
    }

    var showPermissionRationale = false
    var permissionToShowRationaleFor = ""

    permissions.forEach { permission ->
        if (shouldShowPermissionRationale(
                context,
                permission
            )
        ) {
            showPermissionRationale = true
            permissionToShowRationaleFor = permission
        }
    }


    if (showPermissionRationale) {
        Log.d(TAG, "Showing permission rationale for $permissionToShowRationaleFor")

        LaunchedEffect(showPermissionRationale) {

            val snackbarResult = snackbarHostState.showSnackbar(
                message = permissionRationale,
                actionLabel = context.resources.getString(R.string.grant_access),
                duration = SnackbarDuration.Indefinite
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {
                    Log.d(
                        TAG,
                        "User dismissed permission rationale for $permissionToShowRationaleFor"
                    )
                    //User denied the permission, do nothing
                    permissionAction(PermissionAction.OnPermissionDenied)
                }

                SnackbarResult.ActionPerformed -> {
                    Log.d(
                        TAG,
                        "User granted permission for $permissionToShowRationaleFor rationale. Launching permission request.."
                    )
                    launcher.launch(permissions)
                }
            }
        }
    } else {
        //Request permissions again
        Log.d(TAG, "Requesting permission for ${permissions.joinToString(",")} again")
        SideEffect {
            launcher.launch(permissions)
        }

    }
}

