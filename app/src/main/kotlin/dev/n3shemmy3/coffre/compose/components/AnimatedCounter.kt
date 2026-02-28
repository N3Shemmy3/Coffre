package dev.n3shemmy3.coffre.compose.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import dev.n3shemmy3.coffre.domain.model.Digit
import dev.n3shemmy3.coffre.domain.model.compareTo
import java.math.BigDecimal


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    textAlign: TextAlign = TextAlign.End,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(
        modifier = modifier
            .animateContentSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        count
            .mapIndexed { index, c ->
                Digit(
                    c,
                    BigDecimal(count),
                    BigDecimal(index)
                )
            }
            .forEach { digit ->
                AnimatedContent(
                    targetState = digit,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInVertically { -it }.togetherWith(slideOutVertically { it })
                        } else {
                            slideInVertically { it }.togetherWith(slideOutVertically { -it })
                        }
                    }
                ) { digit ->
                    Text(
                        "${digit.digitChar}",
                        style = style,
                        textAlign = textAlign,
                        color = color
                    )
                }
            }
    }
}