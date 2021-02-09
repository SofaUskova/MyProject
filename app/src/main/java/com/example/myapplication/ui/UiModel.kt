package com.example.myapplication.ui

import com.example.myapplication.models.Horse

sealed class UiModel {
    data class HorseItem(val horse: Horse) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
