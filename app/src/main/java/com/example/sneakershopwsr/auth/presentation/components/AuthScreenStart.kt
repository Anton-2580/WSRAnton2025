package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sneakershopwsr.core.presentation.components.TopActionLine
import com.example.sneakershopwsr.ui.theme.BackIcon
import com.example.sneakershopwsr.ui.theme.BackgroundButtonIconColors
import com.example.sneakershopwsr.ui.theme.SubTextDark


@Composable
fun AuthScreenStart(
    textTitle: String,
    textComment: String,
    commentColor: Color = SubTextDark,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement. Vertical = Arrangement.Top,
    horizontalAlignment: Alignment. Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopActionLine(
                leftIcon = BackIcon,
                colors = BackgroundButtonIconColors,
                onLiftButtonClick = onBackButtonClick,
            )

            Text(
                text = textTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
            )

            Text(
                text = textComment,
                color = commentColor,
                textAlign = TextAlign.Center,
            )
        }

        content()
    }
}