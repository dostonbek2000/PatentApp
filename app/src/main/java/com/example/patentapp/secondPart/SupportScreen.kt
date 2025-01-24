package com.example.patentapp.secondPart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patentapp.R
import com.example.patentapp.ui.theme.BackgroundColor
import com.example.patentapp.ui.theme.LanguageBackground

@Composable
fun SupportScreen(cardA:String,cardB:String,nameA:String,nameB:String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LanguageBackground),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.CenterStart, // Aligns content in the center
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                )
                .background(color = BackgroundColor) // Background respects the clipping
        ) {
            Text(
                "Поддержать",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                color = Color.White,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 30.dp)
        ) {
            Text(
                "Ваши донаты помогают нам развиваться и создавать ещё более крутые проекты. Спасибо за вашу поддержку! ❤\uFE0F",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = nameA,
                fontSize = 16.sp,
               modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .border(
                        shape = RoundedCornerShape(14.dp),
                        color = BackgroundColor,
                        width = 0.5.dp
                    )
                    .padding(vertical = 4.dp, horizontal = 6.dp),
            ) {

                Text(
                    text = cardA,

                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = nameB,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Start)

            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .border(
                        shape = RoundedCornerShape(14.dp),
                        color = BackgroundColor,
                        width = 0.5.dp
                    )
                    .padding(vertical = 4.dp, horizontal = 6.dp),
            ) {

                Text(
                    text = cardB,

                    fontSize = 24.sp,
                    color = Color.Black,
                     modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }

}