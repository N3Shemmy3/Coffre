package dev.n3shemmy3.coffre.ui.component.base

import androidx.compose.ui.graphics.vector.ImageVector

class AppBarItem(
    val id: String,
    val icon: ImageVector,
    val contentDescription: String?,
    val onClick: () -> Unit,
    val isEnabled: Boolean = true
) {

}