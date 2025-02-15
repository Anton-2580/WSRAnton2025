package com.example.sneakershopwsr.shop.presentation.home

import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.presentation.components.BadgeButton
import com.example.sneakershopwsr.shop.domain.BigMenuItems
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.domain.ShopHomeActions
import com.example.sneakershopwsr.shop.presentation.CategoryState
import com.example.sneakershopwsr.shop.presentation.components.BigMenu
import com.example.sneakershopwsr.shop.presentation.components.Product
import com.example.sneakershopwsr.shop.presentation.components.ShopScaffold
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Background
import com.example.sneakershopwsr.ui.theme.BasketIcon
import com.example.sneakershopwsr.ui.theme.Text
import com.example.sneakershopwsr.ui.theme.MenuIcon
import com.example.sneakershopwsr.ui.theme.WhiteButtonColors
import kotlinx.coroutines.delay


const val EXIT = true
const val ENTRANCE = false


fun <T> Transition.Segment<Boolean>.animateMenu(): FiniteAnimationSpec<T> =
    if (EXIT isTransitioningTo ENTRANCE) {
        tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    } else {
        tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    }


@Composable
fun ShopHomeScreen(
    onIconAction: (BottomMenuIcons) -> Unit,
    onActions: (ShopHomeActions) -> Unit,
    onSelectCategory: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val currentState = remember { mutableStateOf(EXIT) }
    val transitionAnim = updateTransition(
        targetState = currentState.value,
        label = "",
    )

    @Composable
    fun <T, V : AnimationVector> animateValue(
        typeConverter: TwoWayConverter<T, V>,
        label: String = "",
        exitValue: T,
        entranceValue: T,
    ) = transitionAnim.animateValue(
        typeConverter = typeConverter,
        label = label,
        transitionSpec = { animateMenu() },
        targetValueByState = {
            if (it) exitValue else entranceValue
        },
    )

    val scale = animateValue(
        typeConverter = Float.VectorConverter,
        exitValue = 1f,
        entranceValue = 0.75f,
    )
    val rotation = animateValue(
        typeConverter = Float.VectorConverter,
        exitValue = 0f,
        entranceValue = -5f,
    )
    val offset = animateValue(
        typeConverter = IntOffset.VectorConverter,
        exitValue = IntOffset(0, 0),
        entranceValue = IntOffset(750, 0),
    )
    val animDp = animateValue(
        typeConverter = Dp.VectorConverter,
        exitValue = 0.dp,
        entranceValue = 40.dp,
    )

    val action: MutableState<BigMenuItems?> = remember { mutableStateOf(null) }
    LaunchedEffect(action.value) {
        val value = action.value
        if (value !== null) {
            delay(1000L)

            when (value) {
                BigMenuItems.Profile -> onIconAction(BottomMenuIcons.Profile)
                BigMenuItems.Basket -> onIconAction(BottomMenuIcons.Basket)
                BigMenuItems.Favorite -> onIconAction(BottomMenuIcons.Favorites)
                BigMenuItems.Orders -> {}
                BigMenuItems.Notify -> onIconAction(BottomMenuIcons.Notifications)
                BigMenuItems.Settings -> {}
                BigMenuItems.Logout -> {}
            }
        }
    }

    Box {
        BigMenu(
            onActions = {
                currentState.value = !currentState.value
                action.value = it
            },
        )

        ShopHomeScreenBody(
            categoryState = viewModel.categoryState,
            products = viewModel.products,
            onIconAction = onIconAction,
            onActions = onActions,
            onSelectCategory = onSelectCategory,
            onGoToMenu = { currentState.value = !currentState.value },
            modifier = Modifier
                .scale(scale.value)
                .rotate(rotation.value)
                .offset { offset.value }
                .clip(RoundedCornerShape(animDp.value)),
        )
    }
}


@Composable
fun ShopHomeScreenBody(
    categoryState: State<CategoryState>,
    products: State<List<ProductInfoWithImages>>,
    onIconAction: (BottomMenuIcons) -> Unit,
    onActions: (ShopHomeActions) -> Unit,
    onSelectCategory: (Int) -> Unit,
    onGoToMenu: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ShopScaffold(
        onIconAction = onIconAction,
        selected = BottomMenuIcons.Home,
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
    ) { spacer ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onGoToMenu,
                ) {
                    Icon(
                        imageVector = MenuIcon,
                        contentDescription = null,
                    )
                }

                Text(
                    text = stringResource(R.string.home),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Text,
                )

                BadgeButton(
                    onClick = { onIconAction(BottomMenuIcons.Basket) },
                    icon = BasketIcon,
                    color = Background,
                )
            }

            Text(stringResource(R.string.categories))
            LazyRow {
                items(categoryState.value.categories) {
                    Button(
                        onClick = { it.id?.let { id -> onSelectCategory(id) } },
                        colors = WhiteButtonColors,
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(it.name)
                    }
                }
            }

            RowBlock(stringResource(R.string.popular)) { onActions(ShopHomeActions.OnMorePopular) }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(products.value) {
                    Product(
                        productInfo = it,
                        onClick = {},
                        onAddClick = {},
                    )
                }
            }

            RowBlock(stringResource(R.string.stocks)) { onActions(ShopHomeActions.OnMoreStocks) }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Red)
            ) {
                Text(text = "Ad")
            }

        spacer()
    }
}


@Composable
private fun RowBlock(
    text: String,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
        Text(buildAnnotatedString {
            withLink(
                link = LinkAnnotation.Clickable(
                    tag = "",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = Accent,
                        )
                    ),
                    linkInteractionListener = { onClick() },
                ),
            ) {
                append(stringResource(R.string.more))
            }
        })
    }
}