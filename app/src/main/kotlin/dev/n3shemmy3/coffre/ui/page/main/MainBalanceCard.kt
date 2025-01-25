package dev.n3shemmy3.coffre.ui.page.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.ProgressIndicatorDefaults.drawStopIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.theme.ExtendedDarkColorScheme
import dev.n3shemmy3.coffre.ui.theme.ExtendedLightColorScheme
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBalanceCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(
                top = Spacing_content_horizontal, bottom = Spacing_content_horizontal
            ), tonalElevation = 4.dp, shape = MaterialTheme.shapes.extraLarge

    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        ) {
            val (accountName, balanceCurrency, balanceRound, balanceDecimal, incomeTitle, incomeValue, expenseTitle, expenseValue, balanceBar) = createRefs()
            Text(
                text = stringResource(R.string.expenses),
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .constrainAs(accountName) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })
            Text(
                text = "$",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .alpha(.72f)
                    .constrainAs(balanceCurrency) {
                        start.linkTo(accountName.start)
                        top.linkTo(balanceDecimal.top)
                        bottom.linkTo(balanceDecimal.bottom)
                    })
            Text(
                text = "323.",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .constrainAs(balanceRound) {
                        top.linkTo(accountName.top, 24.dp)
                        end.linkTo(balanceDecimal.start, 2.dp)
                    })

            Text(
                text = "45",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .constrainAs(balanceDecimal) {
                        end.linkTo(parent.end)
                        bottom.linkTo(balanceRound.bottom, 4.dp)
                    })
            Text(
                text = stringResource(R.string.income),
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .alpha(.72f)
                    .constrainAs(incomeTitle) {
                        start.linkTo(accountName.start)
                        top.linkTo(balanceRound.bottom, margin = 16.dp)
                    })
            Text(
                text = "+ $4340",
                fontWeight = FontWeight.W700,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .alpha(.72f)
                    .constrainAs(incomeValue) {
                        top.linkTo(incomeTitle.top)
                        end.linkTo(balanceDecimal.end)
                        bottom.linkTo(incomeTitle.bottom)
                    })
            Text(
                text = stringResource(R.string.expenses),
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .alpha(.72f)
                    .constrainAs(expenseTitle) {
                        start.linkTo(accountName.start)
                        top.linkTo(incomeTitle.bottom, margin = 12.dp)
                    })
            Text(
                text = "- $2340",
                fontWeight = FontWeight.W700,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .alpha(.72f)
                    .constrainAs(expenseValue) {
                        top.linkTo(expenseTitle.top)
                        end.linkTo(balanceDecimal.end)
                        bottom.linkTo(expenseTitle.bottom)
                    })
            val successColor = if (isSystemInDarkTheme()) ExtendedDarkColorScheme.success.color else ExtendedLightColorScheme.success.color
            LinearProgressIndicator(
                progress = { .7f },
                color = MaterialTheme.colorScheme.error,
                trackColor = successColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .constrainAs(balanceBar) {
                        start.linkTo(expenseTitle.start)
                        top.linkTo(expenseTitle.bottom, margin = 16.dp)
                    },
                drawStopIndicator = {
                    drawStopIndicator(
                        drawScope = this,
                        stopSize = ProgressIndicatorDefaults.LinearTrackStopIndicatorSize,
                        color = successColor,
                        strokeCap = StrokeCap.Round,
                    )
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainBalanceCardPreview() {
    MainBalanceCard()
}