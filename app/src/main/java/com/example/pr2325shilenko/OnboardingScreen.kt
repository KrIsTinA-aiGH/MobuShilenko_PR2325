package com.example.pr2325shilenko

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.border


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onSkipClick: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            title = "Анализы",
            description = "Экспресс сбор и получение проб",
            imageRes = R.drawable.i4
        ),
        OnboardingPage(
            title = "Уведомления",
            description = "Вы быстро узнаете о результатах",
            imageRes = R.drawable.i3
        ),
        OnboardingPage(
            title = "Мониторинг",
            description = "Наши врачи всегда наблюдают\nза вашими показателями здоровья",
            imageRes = R.drawable.i2
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == pages.size - 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Верхняя часть: кнопка "Пропустить"/"Завершить" + логотип
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            TextButton(
                onClick = { onSkipClick() }
            ) {
                Text(
                    text = if (isLastPage) "Завершить" else "Пропустить",
                    color = Color(0xFF4A90E2),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Image(
                painter = painterResource(id = R.drawable.i1),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Горизонтальный свайп между экранами
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            OnboardingPageContent(pages[page], pagerState.currentPage, pages.size)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPageContent(page: OnboardingPage, currentPage: Int, totalPages: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Заголовок
        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF00C853)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Описание
        Text(
            text = page.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        DotsIndicator(totalPages, currentPage)

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun DotsIndicator(totalPages: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalPages) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == currentPage) Color(0xFF4A90E2)
                        else Color.Transparent
                    )
                    .then(
                        if (index != currentPage)
                            Modifier.border(
                                width = 1.5.dp,
                                color = Color(0xFF4A90E2),
                                shape = CircleShape
                            )
                        else Modifier
                    )
            )
        }
    }
}