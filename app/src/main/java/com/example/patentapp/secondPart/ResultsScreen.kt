package com.example.patentapp.secondPart
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.patentapp.ui.theme.BackgroundColor

@Composable
fun ResultsScreen(score: Int, totalQuestions: Int,onRestart: () -> Unit,navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize() .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Soft gray background
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Викторина завершена!",
                        color = Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
                    )

                    // Score Display
                    Text(
                        text = "Ваш результат:",
                        color = Color.Black,
                        textAlign = TextAlign.Center, fontSize = 16.sp
                    )
                    Text(
                        text = "$score / $totalQuestions",
                        color = BackgroundColor, fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )

                    // Feedback Message
                    Text(
                        text = "В следующий раз вам повезёт больше!",
                        color = Color.Black, // Accent Blue

                        textAlign = TextAlign.Center
                    )

                    // Restart Button
                    Button(
                        onClick = { onRestart() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor) // Blue button
                    ) {
                        Text(
                            text = "Начать заново",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
                navController.navigate("support")
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 40.dp).clip(
                RoundedCornerShape(10.dp)
            ).height(48.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Поддержать", fontSize = 20.sp)
        }
    }
}

