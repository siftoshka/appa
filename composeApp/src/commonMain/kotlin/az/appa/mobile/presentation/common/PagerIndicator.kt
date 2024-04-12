package az.appa.mobile.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pageCount: Int,
    indicatorSize: Dp = 16.dp,
    indicatorShape: RoundedCornerShape = CircleShape,
    space: Dp = 8.dp,
    activeColor: Color,
    inactiveColor: Color,
    orientation: IndicatorOrientation = IndicatorOrientation.Horizontal,
) {
    val listState = rememberLazyListState()

    val totalWidth: Dp = indicatorSize * pageCount + space * (pageCount - 1)
    val widthInPx = LocalDensity.current.run { indicatorSize.toPx() }

    val currentItem = remember {
        derivedStateOf { pagerState.currentPage }
    }

    val itemCount = pagerState.pageCount

    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        if (orientation == IndicatorOrientation.Horizontal) {
            listState.animateScrollToItem(
                currentItem.value,
                (widthInPx / 2 - viewportSize.width / 2).toInt()
            )
        } else {
            listState.animateScrollToItem(
                currentItem.value,
                (widthInPx / 2 - viewportSize.height / 2).toInt()
            )
        }
    }

    if (orientation == IndicatorOrientation.Horizontal) {
        LazyRow(
            modifier = modifier.width(totalWidth),
            state = listState,
            contentPadding = PaddingValues(vertical = space),
            horizontalArrangement = Arrangement.spacedBy(space),
            userScrollEnabled = false
        ) {
            indicatorItems(
                itemCount,
                currentItem.value,
                pageCount,
                indicatorShape,
                activeColor,
                inactiveColor,
                indicatorSize,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.height(totalWidth),
            state = listState,
            contentPadding = PaddingValues(horizontal = space),
            verticalArrangement = Arrangement.spacedBy(space),
            userScrollEnabled = false
        ) {
            indicatorItems(
                itemCount,
                currentItem.value,
                pageCount,
                indicatorShape,
                activeColor,
                inactiveColor,
                indicatorSize,
            )
        }
    }
}

private fun LazyListScope.indicatorItems(
    itemCount: Int,
    currentItem: Int,
    pageCount: Int,
    indicatorShape: RoundedCornerShape,
    activeColor: Color,
    inactiveColor: Color,
    indicatorSize: Dp,
) {
    items(itemCount) { index ->

        val isSelected = (index == currentItem)

        // Index of item in center when odd number of indicators are set
        // for 5 indicators this is 2nd indicator place
        val centerItemIndex = pageCount / 2

        val right1 =
            (currentItem < centerItemIndex &&
                    index >= pageCount - 1)

        val right2 =
            (currentItem >= centerItemIndex &&
                    index >= currentItem + centerItemIndex &&
                    index < itemCount - centerItemIndex + 1)
        val isRightEdgeItem = right1 || right2

        val isLeftEdgeItem =
            index <= currentItem - centerItemIndex &&
                    currentItem > centerItemIndex &&
                    index < itemCount - pageCount + 1

        Box(modifier = Modifier
            .graphicsLayer {
                val scale = if (isSelected) {
                    1f
                } else if (isLeftEdgeItem || isRightEdgeItem) {
                    .5f
                } else {
                    .8f
                }
                scaleX = scale
                scaleY = scale

            }
            .clip(indicatorShape)
            .size(indicatorSize)
            .background(
                color = if (isSelected) activeColor else inactiveColor,
                shape = indicatorShape
            )
            .then(Modifier)
        )
    }
}

enum class IndicatorOrientation {
    Horizontal, Vertical
}