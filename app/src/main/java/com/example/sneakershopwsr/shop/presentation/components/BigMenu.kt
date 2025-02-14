package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.sneakershopwsr.shop.domain.BigMenuItems
import com.example.sneakershopwsr.ui.theme.Accent


@Composable
fun BigMenu(
    modifier: Modifier = Modifier,
    onActions: (BigMenuItems) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Accent)
            .padding(start = 10.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Box {
                SubcomposeAsyncImage(
                    model = "",
                    contentDescription = "",
                    loading = { CircularProgressIndicator() }
                )
                Text(text = "name")
            }
        }

        items(BigMenuItems.entries.toTypedArray()) {
            if (it.isStartNewSection)
                HorizontalDivider(modifier = Modifier.padding(20.dp))

            Text(it.name)
        }
    }
}
