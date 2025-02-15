package com.example.sneakershopwsr.auth.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreenStart
import com.example.sneakershopwsr.auth.presentation.components.AuthSecureTextField
import com.example.sneakershopwsr.auth.presentation.components.BottomMessage
import com.example.sneakershopwsr.auth.presentation.components.EmailTextField
import com.example.sneakershopwsr.auth.presentation.components.ErrorDialog
import com.example.sneakershopwsr.auth.presentation.components.PasswordTextField
import com.example.sneakershopwsr.core.data.network.NetworkEvents
import com.example.sneakershopwsr.ui.theme.AccentButtonColors
import com.example.sneakershopwsr.ui.theme.Background
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Hint
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AuthRegistrationScreen(
    onSuccessfulRegistration: () -> Unit,
    onBackButtonClick: () -> Unit,
    onGoToLogin: () -> Unit,
    viewModel: AuthRegisterViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .background(Block)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AuthScreenStart(
                textTitle = stringResource(R.string.registration),
                textComment = stringResource(R.string.auth_comment),
                onBackButtonClick = onBackButtonClick,
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                val textDialog: MutableState<String?> = remember { mutableStateOf(null) }
                val typeTextDialog: MutableState<String?> = remember { mutableStateOf(null) }
                val textFieldModifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(30))
                    .background(Background)

                AuthSecureTextField(
                    state = viewModel.loginRegisterState.value.name,
                    modifier = textFieldModifier,
                    textFieldName = stringResource(R.string.name),
                    visibleTextObfuscationMode = true,
                    keyboardType = KeyboardType.Email,
                )

                EmailTextField(
                    state = viewModel.loginRegisterState.value.email,
                    modifier = textFieldModifier,
                )
                PasswordTextField(
                    state = viewModel.loginRegisterState.value.password,
                    modifier = textFieldModifier,
                )

                LaunchedEffect(Unit) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is NetworkEvents.Error -> {
                                textDialog.value = it.message
                                typeTextDialog.value = "Error"
                            }
                            NetworkEvents.Loading -> {}
                            NetworkEvents.Successful -> onSuccessfulRegistration()
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

                val colors = if (viewModel.loginRegisterState.value.canSign) AccentButtonColors
                else AccentButtonColors.copy(disabledContainerColor = Hint)
                Button(
                    onClick = { viewModel.signIn() },
                    colors = colors,
                    enabled = viewModel.loginRegisterState.value.canSign,
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = stringResource(R.string.sign_up))
                }
            }

            BottomMessage(
                onClick = onGoToLogin,
                textUsually = stringResource(R.string.have_accaunt),
                textLink = stringResource(R.string.go_to_login),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}