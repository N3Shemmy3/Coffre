package dev.n3shemmy3.coffre.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    color: Color = CardDefaults.cardColors().containerColor,
    contentColor: Color = CardDefaults.cardColors().contentColor,
    onClick: () -> Unit = {},
    actionContent: @Composable (() -> Unit) = {},
    content: @Composable (() -> Unit) = {},
) {
    Surface(
        onClick = onClick,
        modifier =
            modifier
                .fillMaxWidth()
                .minimumInteractiveComponentSize()
                .wrapContentHeight(),
        color = color,
        contentColor = contentColor,
        shape = shape
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                content.invoke()
            }
            Box {
                actionContent.invoke()
            }

        }
    }
}