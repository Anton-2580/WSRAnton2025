package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.request.crossfade
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.shop.domain.SwipeableProductActions
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.AddIcon
import com.example.sneakershopwsr.ui.theme.Background
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.MinusIcon
import com.example.sneakershopwsr.ui.theme.Red
import com.example.sneakershopwsr.ui.theme.TrashIcon
import kotlinx.coroutines.launch


@Composable
fun SwipeableProduct(
    product: ProductInfoWithImages,
    onActions: (SwipeableProductActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    val contextButtonWidth = remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }

    val painter = rememberAsyncImagePainter(
        model = product.images[0].url,
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .crossfade(true)
            .build()
    )

    val onDeletedKey = remember { mutableStateOf(true) }

    val animate = suspend {
        if (offset.value >= contextButtonWidth.floatValue / 2) {
            offset.animateTo(contextButtonWidth.floatValue)
        } else if (offset.value <= -contextButtonWidth.floatValue / 2) {
            offset.animateTo(-contextButtonWidth.floatValue)
        } else {
            offset.animateTo(0f)
        }
    }

    val buttonModifier = Modifier
        .fillMaxHeight()
        .clip(RoundedCornerShape(10))
        .width(70.dp)
        .padding(horizontal = 1.dp)


    LaunchedEffect(onDeletedKey, contextButtonWidth) {
        animate()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = buttonModifier
                .align(Alignment.CenterStart)
                .background(Accent),
        ) {
            IconButton(
                onClick = { onActions(SwipeableProductActions.OnPlusClick) },
            ) {
                Icon(
                    imageVector = AddIcon,
                    contentDescription = "",
                    tint = Block,
                )
            }

            Text(text = "1")

            IconButton(
                onClick = { onActions(SwipeableProductActions.OnMinusClick) },
            ) {
                Icon(
                    imageVector = MinusIcon,
                    contentDescription = "",
                    tint = Block,
                )
            }
        }

        Box(
            modifier = buttonModifier
                .align(Alignment.CenterEnd)
                .onSizeChanged { contextButtonWidth.floatValue = it.width * 1.3f }
                .background(Red)
                .clickable {
                    onActions(SwipeableProductActions.OnDeleteClick)
                    onDeletedKey.value = !onDeletedKey.value
                },
        ) {
            Icon(
                imageVector = TrashIcon,
                contentDescription = "",
                tint = Block,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Surface(
            shape = RoundedCornerShape(5),
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(x = offset.value.toInt(), y = 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                offset.snapTo(
                                    (offset.value + dragAmount).coerceIn(
                                        -contextButtonWidth.floatValue,
                                        contextButtonWidth.floatValue
                                    )
                                )
                            }
                        },
                        onDragEnd = {
                            scope.launch {
                                animate()
                            }
                        },
                    )
                },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Block),
            ) {
                Box(
                    modifier = modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20))
                        .background(Background)
                        .weight(0.3f),
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "",
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .weight(0.7f),
                ) {
                    Text(text = product.name)
                    Text(text = "₽${product.price}")
                }
            }
        }
    }
}

