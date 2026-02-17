package dev.n3shemmy3.coffre.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.common.CategoryItem
import dev.n3shemmy3.coffre.ui.common.LeadingIcon
import dev.n3shemmy3.coffre.ui.common.ListItem
import dev.n3shemmy3.coffre.ui.common.TonalChip
import dev.n3shemmy3.coffre.ui.common.TonalChipColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }, actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = stringResource(R.string.search)
                        )
                    }
                }, scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(0.dp, 16.dp),
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.new_transaction)
                )
            }
        }

    ) { paddings ->
        LazyColumn(
            Modifier.padding(paddings),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            val largeCorner = 16.dp
            val smallCorner = 4.dp

            item {
                Box(Modifier.padding(bottom = 12.dp)) {
                    Card(
                        shape = RoundedCornerShape(largeCorner), colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {

                        Column(
                            Modifier
                                .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)
                                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val paddingEnd = 12.dp
                            val balanceColor = MaterialTheme.colorScheme.onSurface
                            val balanceStyle = MaterialTheme.typography.displayMedium
                            val labelStyle = MaterialTheme.typography.bodyMedium
                            val spentColor = MaterialTheme.colorScheme.error
                            val textAlign = TextAlign.End

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text("My Balance", style = MaterialTheme.typography.labelLarge)
                                IconButton(onClick = {}) {
                                    Icon(Icons.Outlined.Visibility, "Hide figures")
                                }
                            }

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, end = 12.dp, bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text("£", style = balanceStyle)

                                Text(
                                    buildAnnotatedString {
                                        withStyle(
                                            SpanStyle(
                                                fontSize = balanceStyle.fontSize
                                            )
                                        ) {
                                            append("45,000")
                                        }
                                        withStyle(
                                            SpanStyle(
                                                fontSize = MaterialTheme.typography.displaySmall.fontSize
                                            )
                                        ) {
                                            append(".63")
                                        }
                                    },
                                    style = MaterialTheme.typography.displaySmall,
                                    textAlign = textAlign,
                                    color = balanceColor
                                )

                            }

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(end = paddingEnd),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text("Received", style = labelStyle)
                                Text("£50,782.00", style = labelStyle, textAlign = textAlign)
                            }

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(end = paddingEnd),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Spent", style = labelStyle, color = spentColor
                                )
                                Text(
                                    "£5,782.63",
                                    style = labelStyle,
                                    color = spentColor,
                                    textAlign = textAlign
                                )
                            }
                        }

                    }

                }
            }
            item {
                CategoryItem(
                    shape = RoundedCornerShape(
                        largeCorner,
                        largeCorner,
                        smallCorner,
                        smallCorner
                    ),
                    content = {
                        Text("Transactions")
                    },
                    actionContent = {
                        TextButton(onClick = {}) {
                            Text("See All")
                        }
                    }
                )

            }
            itemsIndexed((1..4).toList()) { index, item ->
                ListItem(
                    leadingContent = {
                        LeadingIcon(Icons.Outlined.CreditCard, "")
                    },
                    content = {
                        Text("Item title", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Item supporting text",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    actionContent = {
                        TonalChip(
                            tonalChipColors = when (index) {
                                0 -> TonalChipColors.Error
                                1 -> TonalChipColors.Secondary
                                2 -> TonalChipColors.Tertiary
                                3 -> TonalChipColors.Secondary
                                else -> {
                                    TonalChipColors.Error
                                }
                            }
                        ) { Text("£19.67", style = MaterialTheme.typography.labelMedium) }
                    }
                )

            }
            item {
                ListItem(
                    shape = RoundedCornerShape(
                        smallCorner,
                        smallCorner,
                        largeCorner,
                        largeCorner
                    ),
                    leadingContent = {
                        LeadingIcon(Icons.Outlined.CreditCard, "")
                    },
                    content = {
                        Text("Item title", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Item supporting text",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    actionContent = {
                        TonalChip { Text("£19.67", style = MaterialTheme.typography.labelMedium) }
                    }
                )


            }
        }
    }
}


@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}