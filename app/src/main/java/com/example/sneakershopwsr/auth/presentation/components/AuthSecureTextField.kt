package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.ui.theme.SubTextDark


@Composable
fun AuthSecureTextField(
    state: TextFieldState,
    textFieldName: String,
    textBottomAction: String? = null,
    onTextBottomActionClick: () -> Unit = {},
    textBottomActionColor: Color = SubTextDark,
    itemSpace: Dp =  5.dp,
    icon: ImageVector? = null,
    onIconClick: () -> Unit = {},
    visibleTextObfuscationMode: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    textStyle: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier,
) {
    val textObfuscationMode = if (visibleTextObfuscationMode) TextObfuscationMode.Visible else TextObfuscationMode.Hidden

    Column(
        verticalArrangement = Arrangement.spacedBy(itemSpace),
    ) {
        Text(textFieldName)
        BasicSecureTextField(
            state = state,
            textObfuscationMode = textObfuscationMode,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            textStyle = textStyle,
            modifier = modifier,
            decorator = { innerBox ->
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                    ) {
                        innerBox()
                    }

                    if (icon !== null) {
                        IconButton(
                            onClick = onIconClick,
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "changeVisibility",
                            )
                        }
                    }
                }
            }
        )

        if (textBottomAction !== null) {
            Text(
                text = buildAnnotatedString {
                    withLink(link = LinkAnnotation.Clickable(
                        tag = "",
                        styles = null,
                        linkInteractionListener = { onTextBottomActionClick() },
                    )) {
                        append(textBottomAction)
                    }
                },
                color = textBottomActionColor,
                modifier = Modifier.align(Alignment.End),
            )
        }
    }
}