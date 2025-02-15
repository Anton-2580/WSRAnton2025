package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreen
import com.example.sneakershopwsr.auth.presentation.components.EmailTextField
import com.example.sneakershopwsr.ui.theme.Background


@Composable
fun AuthPasswordRecoveryScreen(
    onBackButtonClick: () -> Unit,
    viewModel: AuthPasswordRecoveryViewModel = hiltViewModel(),
) {
    AuthScreen(
        onBackButtonClick = onBackButtonClick,
        textTitle = stringResource(R.string.recovery_password),
        textComment = stringResource(R.string.enter_your_ata_for_reset),
        textButton = stringResource(R.string.send),
        viewModel = viewModel,
        onClick = { viewModel.sendOtp() },
    ) {
        EmailTextField(
            state = viewModel.loginRegisterState.value.email,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(30))
                .background(Background),
        )
    }
}