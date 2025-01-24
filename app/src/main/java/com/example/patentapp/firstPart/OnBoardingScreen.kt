package com.example.patentapp.firstPart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import com.example.patentapp.ui.theme.BackgroundColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0)
    val (selectedPage, setSelectedPage) = remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {
        // Horizontal Pager (Dots Indicator)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in listData.indices) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(2.dp)
                        .size(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (i == selectedPage) MaterialTheme.colorScheme.background
                            else MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                        )
                )
            }
        }

        // Horizontal Pager
        HorizontalPager(
            count = listData.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Display the image
                Image(
                    painter = painterResource(id = listData[page].image),
                    contentDescription = listData[page].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                        .aspectRatio(1f)
                )

                // Display the title
                Text(
                    text = listData[page].title,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp).align(Alignment.Start),
                )

                // Display the description
                Text(
                    text = listData[page].desc,
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        // Navigation Button
        Button(
            onClick = {
                if (selectedPage == listData.size - 1) {
                    onFinish() // Corrected from `onFinish` to `onFinish()`
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(selectedPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = if (selectedPage == listData.size - 1) "продолжать" else "продолжать",
                color = Color.Black
            )
        }
    }
}
