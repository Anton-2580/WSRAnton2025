package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.auth.presentation.BaseAuthViewModel
import com.example.sneakershopwsr.core.data.network.BaseEvents
import com.example.sneakershopwsr.core.presentation.components.getNewPaddingValues
import com.example.sneakershopwsr.ui.theme.AccentButtonColors
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Hint
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AuthScreen(
    onBackButtonClick: () -> Unit,
    textTitle: String,
    textComment: String,
    textButton: String? = null,
    onClick: () -> Unit = {},
    viewModel: BaseAuthViewModel,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    onNetworkLoading: () -> Unit = {},
    onNetworkSuccessful: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
    modifier: Modifier = Modifier,
    fields: @Composable () -> Unit,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Block)
                .padding(getNewPaddingValues(
                    innerPadding = innerPadding,
                    horizontalPadding = 20.dp,
                ))
        ) {
            AuthScreenStart(
                textTitle = textTitle,
                textComment = textComment,
                onBackButtonClick = onBackButtonClick,
                modifier = modifier,
                verticalArrangement = verticalArrangement,
            ) {
                fields()

                val textDialog: MutableState<String?> = remember { mutableStateOf(null) }
                val typeTextDialog: MutableState<String?> = remember { mutableStateOf(null) }
                LaunchedEffect(Unit) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is BaseEvents.Error -> {
                                textDialog.value = it.message
                                typeTextDialog.value = "Error"
                            }
                            BaseEvents.Loading -> onNetworkLoading()
                            BaseEvents.Successful -> onNetworkSuccessful()
                        }
                    }
                }

                if (textDialog.value !== null && typeTextDialog.value !== null) {
                    ErrorDialog(
                        onDismissRequest = { textDialog.value = null },
                        text = textDialog.value!!,
                        title = typeTextDialog.value!!,
                    )
                }

                if (textButton !== null) {
                    val colors = if (viewModel.loginRegisterState.value.canSign) AccentButtonColors
                                 else AccentButtonColors.copy(disabledContainerColor = Hint)
                    Button(
                        onClick = onClick,
                        colors = colors,
                        enabled = viewModel.loginRegisterState.value.canSign,
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = textButton)
                    }
                }
            }

            content()
        }
    }
}