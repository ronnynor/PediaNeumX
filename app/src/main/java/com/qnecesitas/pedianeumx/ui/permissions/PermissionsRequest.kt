package com.qnecesitas.pedianeumx.ui.permissions

data class PermissionsRequest(
    val permissions: Array<String>,
    val permissionsRationale: String,
    val permissionAction: (PermissionAction) -> Unit
) {
    fun isRequestAuthorized( permissionStates: Map<String, Boolean>) : Boolean {
        val authorized = permissionStates.filter { permissions.contains(it.key) }.all { it.value }
        return authorized
    }
}