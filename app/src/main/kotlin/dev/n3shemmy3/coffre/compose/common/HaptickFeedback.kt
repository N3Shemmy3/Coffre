package dev.n3shemmy3.coffre.compose.common

import android.view.HapticFeedbackConstants
import android.view.View

object HapticFeedback {
    fun View.shortPressHapticFeedback() = this.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)

    fun View.longPressHapticFeedback() =
        this.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
}
