package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.example.sneakershopwsr.ui.theme.Hint


@Composable
fun BottomMessage(
    textUsually: String,
    textLink: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Hint,
            )) {
                append(textUsually)
            }

            withLink(
                link = LinkAnnotation.Clickable(
                    tag = "",
                    styles = null,
                    linkInteractionListener = { onClick() },
                )
            ) {
                append(textLink)
            }
        },
    )
}