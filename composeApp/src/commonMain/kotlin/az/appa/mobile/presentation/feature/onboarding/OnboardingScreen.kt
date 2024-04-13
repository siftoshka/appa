package az.appa.mobile.presentation.feature.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.app_name
import appa.composeapp.generated.resources.btn_next
import appa.composeapp.generated.resources.btn_skip
import appa.composeapp.generated.resources.btn_start
import az.appa.mobile.presentation.common.OutlinedButton
import az.appa.mobile.presentation.common.PagerIndicator
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onNavLogin: () -> Unit
) {
    val viewModel = koinInject<OnboardingViewModel>()

    val pages = listOf(OnboardingPage.First, OnboardingPage.Second, OnboardingPage.Third)
    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                OnboardingContract.Effect.OpenLoginScreen -> {
                    onNavLogin()
                }
            }
        }
    }

    Surface(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing), horizontalAlignment = Alignment.CenterHorizontally) {
            SkipButton(pagerState = pagerState, size = pages.size - 1)
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                pageSpacing = 0.dp,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    state = pagerState,
                    orientation = Orientation.Horizontal
                ),
            ) { position ->
                PagerScreen(page = pages[position])
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PagerIndicator(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.default),
                    activeColor = MaterialTheme.colorScheme.primary,
                    inactiveColor = MaterialTheme.colorScheme.outline,
                    indicatorSize = 6.dp,
                    pagerState = pagerState,
                    pageCount = pages.size
                )
                NextButton(pagerState = pagerState) {
                    if (pagerState.currentPage == 2) {
                        viewModel.setEvent(OnboardingContract.Event.OnFinishOnboarding)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PagerScreen(page: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(page.image),
                contentDescription = stringResource(Res.string.app_name)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        }
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.default)) {
            Text(
                text = stringResource(page.title),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 26.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(page.desc),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
private fun NextButton(
    pagerState: PagerState,
    onPerformClick: () -> Unit
) {
    val lastPage = pagerState.currentPage == 2
    val fraction by animateFloatAsState(if (lastPage) 1f else 0.5f)
    val backgroundColor = if (lastPage) MaterialTheme.colorScheme.primary
    else Color.Transparent
    val text = if (lastPage) Res.string.btn_start else Res.string.btn_next
    val textColor = if (lastPage) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onBackground

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth(fraction)
            .height(80.dp)
            .padding(MaterialTheme.spacing.default),
        backgroundColor = backgroundColor,
        text = stringResource(text),
        textColor = textColor
    ) {
        onPerformClick()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
private fun SkipButton(pagerState: PagerState, size: Int) {
    val scope = rememberCoroutineScope()

    val enabled = pagerState.currentPage != 2
    val text = if (enabled) stringResource(Res.string.btn_skip) else ""
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.default),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            enabled = enabled,
            shape = MaterialTheme.shapes.large,
            onClick = { scope.launch { pagerState.animateScrollToPage(size) } }) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ultraLarge))
    }
}