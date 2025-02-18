package com.example.sneakershopwsr.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreen
import com.example.sneakershopwsr.auth.presentation.components.BottomMessage
import com.example.sneakershopwsr.auth.presentation.components.EmailTextField
import com.example.sneakershopwsr.auth.presentation.components.PasswordTextField
import com.example.sneakershopwsr.ui.theme.Background


@Composable
fun AuthLoginScreen(
    onSuccessfulLogin: () -> Unit,
    onBackButtonClick: () -> Unit,
    onGoToRegister: () -> Unit,
    onPasswordRecovery: () -> Unit,
    viewModel: AuthLoginViewModel = hiltViewModel(),
) {
    AuthScreen(
        content = {
            BottomMessage(
                onClick = onGoToRegister,
                textUsually = stringResource(R.string.is_first),
                textLink = stringResource(R.string.go_to_resistration),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.BottomCenter)
            )
        },
        onBackButtonClick = onBackButtonClick,
        textTitle = stringResource(R.string.authorization),
        textComment = stringResource(R.string.auth_comment),
        textButton = stringResource(R.string.sign_in),
        viewModel = viewModel,
        onClick = { viewModel.signIn() },
        onNetworkSuccessful = onSuccessfulLogin,
    ) {
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(30))
            .background(Background)

        EmailTextField(
            state = viewModel.loginRegisterState.value.email,
            modifier = textFieldModifier,
        )
        PasswordTextField(
            state = viewModel.loginRegisterState.value.password,
            modifier = textFieldModifier,
            onTextBottomActionClick = onPasswordRecovery,
        )
    }
}
