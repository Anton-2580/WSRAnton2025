package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit,
    text: String?,
    title: String?,
) {
    if (text !== null && title !== null) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            Surface(
                shape = RoundedCornerShape(20),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(
                        text = title,
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = text)
                }
            }
        }
    }
}