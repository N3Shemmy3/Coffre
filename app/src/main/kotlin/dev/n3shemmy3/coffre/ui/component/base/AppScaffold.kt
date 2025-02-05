package dev.n3shemmy3.coffre.ui.component.base


import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    useLargeAppBar: Boolean = true,
    navigationIcon: (@Composable () -> Unit)? = null,
    title: String,
    content: (@Composable (paddingValues: PaddingValues) -> Unit)? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    actions: MutableList<AppBarItem>
) {
    val scrollBehavior = if (useLargeAppBar) TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    ) else TopAppBarDefaults.pinnedScrollBehavior()
    var isExpanded = scrollBehavior.state.collapsedFraction <= 0.65f
    var layoutDirection = LocalLayoutDirection.current
    val cutOutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemInsets = WindowInsets.systemBars.asPaddingValues()

    val hInsets =
        cutOutInsets.calculateStartPadding(layoutDirection) + cutOutInsets.calculateEndPadding(
            layoutDirection
        )

    fun backIcon() = @Composable() {
        Row(
            modifier = Modifier
                .padding(start = hInsets + Spacing_content_vertical)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationIcon?.invoke()
        }
    }

    fun titleText() = @Composable {
        Row(
            modifier = Modifier
                .padding(
                    start = if (!useLargeAppBar) Spacing_content_vertical else if (isExpanded) hInsets + Spacing_content_vertical else Spacing_content_vertical,
                )
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var textState by remember { mutableStateOf(TextFieldValue(title)) }
            if (scrollBehavior.state.collapsedFraction >= 0.65f) {
                BasicTextField(
                    value = textState,
                    onValueChange = {
                        textState = it
                    },
                    textStyle = MaterialTheme.typography.titleLarge,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { }),

                    )
            } else {
                Text(title)
            }

        }
    }

    fun optionsMenu() = @Composable {
        actions.forEach { item ->
            AppBarIconButton(
                imageVector = item.icon,
                enabled = item.isEnabled,
                onClick = item.onClick,
                contentDescription = item.contentDescription,
            )
        }
        Spacer(
            modifier = Modifier
                .width(hInsets + Spacing_content_vertical)
                .fillMaxHeight()
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (useLargeAppBar) {
                val typography = MaterialTheme.typography
                val overrideTypography =
                    remember(typography) { typography.copy(headlineMedium = typography.displaySmall) }

                MaterialTheme(typography = overrideTypography) {
                    LargeTopAppBar(
                        scrollBehavior = scrollBehavior,
                        navigationIcon = {
                            backIcon().invoke()
                        },
                        title = { titleText().invoke() },
                        actions = {
                            optionsMenu().invoke()
                        },
                        expandedHeight = TopAppBarDefaults.LargeAppBarExpandedHeight + 24.dp,
                    )
                }
            } else {
                TopAppBar(scrollBehavior = scrollBehavior, navigationIcon = {
                    backIcon().invoke()
                }, title = { titleText().invoke() }, actions = {
                    optionsMenu().invoke()
                })
            }
        },
        content = { it ->
            val paddingValues = PaddingValues(
                start = hInsets + Spacing_content_horizontal,
                top = it.calculateTopPadding(),
                end = hInsets + Spacing_content_horizontal,
                bottom = systemInsets.calculateBottomPadding()
            )
            content?.invoke(paddingValues)
        },
        floatingActionButton = {
            Box(
                modifier = Modifier.padding(
                    horizontal = hInsets,
                )
            ) {
                floatingActionButton?.invoke()
            }
        })
}