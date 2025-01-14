package dev.n3shemmy3.coffre.ui.page.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.ui.component.base.FeedBackIcon

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val cutOutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemInsets = WindowInsets.systemBars.asPaddingValues()

    val hInsets =
        cutOutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutOutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        ) + 12.dp
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Box(modifier = Modifier.padding(start = hInsets - 12.dp)) {
                        FeedBackIcon(
                            Icons.AutoMirrored.Outlined.ArrowBack,
                            null,
                            onClick = { navController.popBackStack() })
                    }
                },
                title = { if (scrollBehavior.state.contentOffset == 500f) Text(text = "N3Shemmy3") },
                actions = {
                    Spacer(
                        Modifier
                            .fillMaxHeight()
                            .width(hInsets - 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )

            )
        },
        content = { paddingValues ->
            val paddingValues = PaddingValues(
                start = hInsets,
                top = paddingValues.calculateTopPadding(),
                end = hInsets,
                bottom = systemInsets.calculateBottomPadding()
            )
            LazyColumn {
                item(key = "itemHeader".hashCode()) {
                    ProfileHeader(paddingValues)
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    ) {
                    }
                }
                item(key = "bottomPadding".hashCode()) {
                    Spacer(modifier = Modifier.height(systemInsets.calculateBottomPadding()))
                }
            }
        }
    )
}

@Composable
@Preview
fun ProfilePagePreview() {
    val navController = rememberNavController()
    ProfilePage(navController)

}