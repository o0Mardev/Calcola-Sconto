package com.mardev.calcolasconto.presentation.main.update_dialog

data class UpdateDialogState(
    var showUpdateDialog: Boolean = false,
    var showProgress: Boolean = false,
    var progress: Float = 0f,
    var showButtons: Boolean = true
)
