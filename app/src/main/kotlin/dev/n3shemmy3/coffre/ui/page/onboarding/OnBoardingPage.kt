package dev.n3shemmy3.coffre.ui.page.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_vertical
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingPage(navController: NavHostController) {
    var layoutDirection = LocalLayoutDirection.current
    val cutOutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutOutInsets.calculateStartPadding(layoutDirection) + cutOutInsets.calculateEndPadding(
            layoutDirection
        )
    var pageCount = 3
    val pagerState = rememberPagerState(pageCount = { pageCount })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = hInsets, vertical = systemInsets.calculateBottomPadding())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                pageSpacing = 4.dp,
                modifier = Modifier
                    .height(450.dp)
                    .fillMaxWidth(),
            ) { page ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clip(shape = MaterialTheme.shapes.large)
                            .padding(all = 24.dp)
                    )
                    Text(
                        text = "(\u2060・\u2060o\u2060・)",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(Modifier.size(Spacing_page_horizontal))
            DotsIndicator(
                dotCount = pageCount,
                type = ShiftIndicatorType(
                    dotsGraphic = DotGraphic(
                        size = 8.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ),
                pagerState = pagerState
            )
        }
        ActionRow(pagerState)
    }
}

@Composable
fun ActionRow(pagerState: PagerState) {
    val animationScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing_page_horizontal, vertical = Spacing_page_vertical)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var isFinalPage = pagerState.currentPage == pagerState.pageCount - 1
        AnimatedVisibility(visible = !isFinalPage) {
            TextButton(
                onClick = {
                    isFinalPage = true
                }) {
                Text(text = "Skip")
            }
        }
        AnimatedVisibility(visible = isFinalPage) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                animationScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }) {
                Text(text = "Continue")
            }
        }
        AnimatedVisibility(visible = !isFinalPage) {
            Button(onClick = {
                animationScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }) {
                Text(text = "Next")
            }
        }

    }
}

@Preview
@Composable
fun OnBoardingPagePreview() {
    val navController = rememberNavController()
    OnBoardingPage(navController)
}