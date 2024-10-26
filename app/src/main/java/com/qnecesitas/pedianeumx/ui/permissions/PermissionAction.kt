package com.qnecesitas.pedianeumx.ui.permissions

sealed class PermissionAction {

    object OnPermissionGranted : PermissionAction()

    object OnPermissionDenied : PermissionAction()
}