package com.example.sneakershopwsr.auth.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreen
import com.example.sneakershopwsr.auth.presentation.components.AuthSecureTextField
import com.example.sneakershopwsr.auth.presentation.components.BottomMessage
import com.example.sneakershopwsr.auth.presentation.components.EmailTextField
import com.example.sneakershopwsr.auth.presentation.components.PasswordTextField
import com.example.sneakershopwsr.ui.theme.Background


@Composable
fun AuthRegistrationScreen(
    onSuccessfulRegistration: () -> Unit,
    onBackButtonClick: () -> Unit,
    onGoToLogin: () -> Unit,
    viewModel: AuthRegisterViewModel = hiltViewModel(),
) {
    AuthScreen(
        content = {
            BottomMessage(
                onClick = onGoToLogin,
                textUsually = stringResource(R.string.have_accaunt),
                textLink = stringResource(R.string.go_to_login),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.BottomCenter)
            )
        },
        onBackButtonClick = onBackButtonClick,
        textTitle = stringResource(R.string.registration),
        textComment = stringResource(R.string.auth_comment),
        textButton = stringResource(R.string.sign_up),
        viewModel = viewModel,
        onClick = { viewModel.signUp() },
        onNetworkSuccessful = onSuccessfulRegistration,
    ) {
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
            keyboardType = KeyboardType.Text,
        )

        EmailTextField(
            state = viewModel.loginRegisterState.value.email,
            modifier = textFieldModifier,
        )
        PasswordTextField(
            state = viewModel.loginRegisterState.value.password,
            modifier = textFieldModifier,
            textBottomAction = null,
        )
    }
}