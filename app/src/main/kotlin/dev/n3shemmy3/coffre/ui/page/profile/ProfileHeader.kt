package dev.n3shemmy3.coffre.ui.page.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_vertical

@Composable
fun ProfileHeader(paddingValues: PaddingValues) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (headerCover, headerAvatar, headerName, headerUsername) = createRefs()
        val backdropGradientColors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.background,
        )
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp + paddingValues.calculateTopPadding())
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors = backdropGradientColors),
                    )
                }
                .blur(4.dp)
                .alpha(0.2f)
                .constrainAs(headerCover) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        )
        val layoutDirection = LocalLayoutDirection.current
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(144.dp)
                .clip(CircleShape)
                .constrainAs(headerAvatar) {
                    bottom.linkTo(headerName.top, Spacing_content_vertical)
                    start.linkTo(headerUsername.start)
                })
        Text(
            text = "Shemmy",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(headerName) {
                start.linkTo(headerUsername.start)
                bottom.linkTo(headerUsername.top)
            })
        Text(
            text = "N3Shemmy3",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alpha(.72f)
                .constrainAs(headerUsername) {
                    start.linkTo(
                        parent.start, margin = paddingValues.calculateStartPadding(
                            layoutDirection
                        ) + Spacing_page_horizontal
                    )
                    bottom.linkTo(parent.bottom)
                })
    }
}

@Composable
@Preview
fun ProfileHeaderPreview() {
    ProfileHeader(
        paddingValues = PaddingValues(
            horizontal = Spacing_page_horizontal,
            vertical = Spacing_page_vertical
        )
    )
}