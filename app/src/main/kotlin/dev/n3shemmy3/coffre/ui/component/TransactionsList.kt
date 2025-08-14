/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.ui.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.ui.navigation.DURATION_ENTER
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.screen.main.MainScreenState
import dev.n3shemmy3.coffre.util.formatCurrency
import dev.n3shemmy3.coffre.util.formatToLocalTime

@Composable
fun TransactionsList(
    modifier: Modifier,
    state: LazyListState,
    mainState: MainScreenState,
    onAction: (Action) -> Unit,
) {
    LazyColumn(
        state = state,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item(0) {
            Row(Modifier.padding(vertical = 12.dp)) {
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(durationMillis = DURATION_ENTER)
                    )
                ) {
                    BalanceCard(
                        title = stringResource(R.string.my_balance),
                        currency = Typography.euro.toString(),
                        balance = mainState.totalBalance,
                        received = mainState.totalIncomes,
                        spent = mainState.totalExpenses
                    )
                }
            }
        }
        item(1) {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = DURATION_ENTER)
                )
            ) {
                HeaderItem(
                    onActionClick = {
                        onAction(Action.ViewFlow.Open(route = Route.OVERVIEW))
                    },
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 4.dp,
                        bottomStart = 4.dp
                    )
                )
            }
        }
        itemsIndexed(mainState.transactions) { index, item ->
            val bottomRadius = if (index == mainState.transactions.lastIndex) 20.dp else 4.dp
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = DURATION_ENTER)
                )
            ) {
                TwoLineItem(
                    icon = Icons.Outlined.CreditCard,
                    title = item.title,
                    summary = formatToLocalTime(item.time),
                    endText = formatCurrency(item.amount),
                    type = item.type,
                    onClick = {
                        onAction(Action.ViewFlow.Open(route = Route.DETAIL, payload = item))
                    },
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 4.dp,
                        bottomEnd = bottomRadius,
                        bottomStart = bottomRadius
                    )
                )
            }
        }
        item {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(84.dp)
            )
        }
    }
}