package dev.n3shemmy3.coffre.ui.page.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.page.main.RouteName
import dev.n3shemmy3.coffre.ui.theme.CustomRotatingMorphShape
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
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            val (pager, centerSpacer, actionRow) = createRefs()
            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(pager) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            ) { page ->
                Column(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val morph = remember {
                        Morph(
                            RoundedPolygon(
                                12,
                                rounding = CornerRounding(0.2f)
                            ), RoundedPolygon.star(
                                12,
                                rounding = CornerRounding(0.2f)
                            )
                        )
                    }
                    val infiniteTransition = rememberInfiniteTransition("infinite outline movement")
                    val animatedProgress = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            tween(2000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "animatedMorphProgress"
                    )
                    val animatedRotation = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            tween(6000, easing = EaseInOut),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "animatedMorphProgress"
                    )

                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(all = 24.dp)
                            .clip(
                                CustomRotatingMorphShape(
                                    morph,
                                    animatedProgress.value,
                                    animatedRotation.value
                                )
                            )
                            .aspectRatio(1f)
                            .size(120.dp)

                    )
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "Moshi moshi",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            PagerIndicator(
                modifier = Modifier
                    .constrainAs(centerSpacer) {
                        verticalBias = .85f
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }, pagerState
            )

            ActionRow(navController, modifier = Modifier.constrainAs(actionRow) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, pagerState = pagerState)
        }
    }
}

@Composable
fun PagerIndicator(modifier: Modifier, pagerState: PagerState) {
    AnimatedVisibility(
        modifier = modifier,
        visible = pagerState.currentPage != pagerState.pageCount - 1
    ) {
        DotsIndicator(
            dotCount = pagerState.pageCount,
            pagerState = pagerState,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    size = 8.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ),
        )
    }
}

@Composable
fun ActionRow(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val animationScope = rememberCoroutineScope()
    Row(
        modifier = modifier
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
                    animationScope.launch {
                       navController.navigate(RouteName.Currency)
                    }

                }) {
                Text(text = stringResource(R.string.action_skip))
            }
        }
        AnimatedVisibility(visible = isFinalPage) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                animationScope.launch {
                    navController.navigate(RouteName.Currency)
                }
            }) {
                Text(text = stringResource(R.string.action_continue))
            }
        }
        AnimatedVisibility(visible = !isFinalPage) {
            Button(onClick = {
                animationScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }) {
                Text(text = stringResource(R.string.action_next))
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
