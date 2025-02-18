package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.presentation.components.AuthScreen
import com.example.sneakershopwsr.auth.presentation.components.EmailTextField
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Background
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Text
import com.example.sneakershopwsr.ui.theme.Email1Icon
import com.example.sneakershopwsr.ui.theme.SubTextDark


@Composable
fun AuthPasswordRecoveryScreen(
    onBackButtonClick: () -> Unit,
    onGoToNextScreen: (String) -> Unit,
    viewModel: AuthPasswordRecoveryViewModel = hiltViewModel(),
) {
    val goToNextScreen = remember { mutableStateOf(false) }

    if (goToNextScreen.value) {
        Dialog(
            onDismissRequest = {  },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
        ) {
            Box(
                modifier = Modifier
                    .clickable { onGoToNextScreen(
                        viewModel.loginRegisterState.value.email.text.toString()
                    ) },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .background(Block)
                        .padding(horizontal = 30.dp, vertical = 50.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Accent)
                    ) {
                        Icon(
                            imageVector = Email1Icon,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.check_your_email),
                        color = Text,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(R.string.check_your_email_description),
                        color = SubTextDark,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    AuthScreen(
        onBackButtonClick = onBackButtonClick,
        textTitle = stringResource(R.string.recovery_password),
        textComment = stringResource(R.string.enter_your_ata_for_reset),
        textButton = stringResource(R.string.send),
        viewModel = viewModel,
        onClick = { viewModel.sendOtp() },
        onNetworkSuccessful = { goToNextScreen.value = !goToNextScreen.value }
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