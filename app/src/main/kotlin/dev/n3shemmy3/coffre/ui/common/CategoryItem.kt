package dev.n3shemmy3.coffre.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .minimumInteractiveComponentSize()
        .wrapContentHeight(),
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    color: Color = CardDefaults.cardColors().containerColor,
    contentColor: Color = CardDefaults.cardColors().contentColor,
    onClick: () -> Unit = {},
    actionContent: @Composable (() -> Unit) = {},
    content: @Composable (() -> Unit) = {},
) {
    Surface(
        onClick = onClick,
        modifier = modifier, color = color, contentColor = contentColor, shape = shape
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content.invoke()
            actionContent.invoke()

        }
    }
}