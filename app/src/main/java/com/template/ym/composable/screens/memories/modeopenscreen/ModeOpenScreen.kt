package com.template.ym.composable.screens.memories.modeopenscreen

sealed class ModeOpenScreen (val mode:String) {
    object Update: ModeOpenScreen("update")
    object Create: ModeOpenScreen("create")
}