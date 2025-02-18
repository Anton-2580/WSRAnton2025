package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreenStart
import com.example.sneakershopwsr.core.data.network.BaseEvents
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Background
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Red
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AuthPasswordRecoveryCodeScreen(
    onBackButtonClick: () -> Unit,
    onGoToNextScreen: () -> Unit,
    viewModel: AuthPasswordRecoveryCodeViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .background(Block)
                .padding(innerPadding)
        ) {
            AuthScreenStart(
                textTitle = stringResource(R.string.otp),
                textComment = stringResource(R.string.otp_comment),
                onBackButtonClick = onBackButtonClick,
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight()
            ) {
                val defaultDefaultColor = Color.Transparent
                val defaultColor = remember { mutableStateOf(defaultDefaultColor) }
                val showDialog = remember { mutableStateOf(false) }

                if (showDialog.value) AuthPasswordRecoveryCodeDialog(
                    otpState = viewModel.otpState.value,
                    generateNewPassword = { viewModel.generatePassword() },
                    onGoToNextScreen = onGoToNextScreen,
                )

                val code = viewModel.otpState.value.code
                LaunchedEffect(Unit) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is BaseEvents.Error -> {
                                if (code.text.length >= viewModel.maxTextLength)
                                    defaultColor.value = Red
                            }

                            BaseEvents.Loading -> Unit
                            BaseEvents.Successful -> {
                                defaultColor.value = Accent
                                delay(1000L)
                                showDialog.value = !showDialog.value
                            }
                        }
                    }
                }

                BasicTextField(
                    value = code.text.toString(),
                    onValueChange = {
                        if (it.length <= viewModel.maxTextLength)
                            code.setTextAndPlaceCursorAtEnd(it)

                        if (it.length < viewModel.maxTextLength)
                            defaultColor.value = defaultDefaultColor
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    decorationBox = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            repeat(viewModel.maxTextLength) {
                                var color = defaultColor.value

                                if (it == code.text.length) {
                                    color = Accent
                                }

                                val char = code.text.getOrNull(it) ?: ' '
                                val shape = RoundedCornerShape(30)
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(100.dp)
                                        .clip(shape)
                                        .background(Background)
                                        .border(1.dp, color, shape),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = char.toString(),
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun AuthPasswordRecoveryCodeDialog(
    otpState: OtpState,
    generateNewPassword: () -> Unit,
    onGoToNextScreen: () -> Unit,
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30))
                .background(Block),
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                val goToNextScreen = remember { mutableStateOf(false) }

                BasicTextField(
                    state = otpState.password,
                    readOnly = !otpState.changingPassword,
                    decorator = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(30))
                                .background(Background),
                        ) {
                            it()
                            Button(
                                onClick = { goToNextScreen.value = !goToNextScreen.value },
                            ) {
                                Text(
                                    text = stringResource(R.string.copy),
                                )
                            }
                        }
                    }
                )

                val clipboardManager = LocalClipboardManager.current
                LaunchedEffect(goToNextScreen.value) {
                    if (goToNextScreen.value) {
                        clipboardManager.setText(buildAnnotatedString {
                            append(otpState.password.text.toString())
                        })
                        delay(1000L)
                        onGoToNextScreen()
                    }
                }


                Button(
                    onClick = generateNewPassword,
                ) {
                    Text(
                        text = stringResource(R.string.generate_password_and_save),
                    )
                }
            }
        }
    }
}
