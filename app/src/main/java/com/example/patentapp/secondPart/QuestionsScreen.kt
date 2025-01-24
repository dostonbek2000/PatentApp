package com.example.patentapp.secondPart

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.patentapp.data.MainData
import com.example.patentapp.ui.theme.BackgroundColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.patentapp.R
import com.example.patentapp.ui.theme.LanguageBackground
import com.example.patentapp.ui.theme.TextBackground
import com.example.patentapp.ui.theme.TextColorB
import kotlinx.coroutines.delay

@Composable
fun QuestionsScreen(
    categoryId: Int,
    questions: List<MainData>,
    onQuizFinished: (score: Int, totalQuestions: Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var selectedAnswerB by remember { mutableStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var timeLeftInSeconds by remember { mutableStateOf(20 * 60) }

    val currentQuestion = questions[currentQuestionIndex]

    // Timer logic
    LaunchedEffect(key1 = timeLeftInSeconds) {
        if (timeLeftInSeconds > 0) {
            delay(1000L)
            timeLeftInSeconds--
        } else {
            onQuizFinished(score, questions.size)
        }
    }

    // Timer display logic
    val minutes = timeLeftInSeconds / 60
    val seconds = timeLeftInSeconds % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding( horizontal = 24.dp),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Вапросы", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(
                text = String.format("%02d:%02d", minutes, seconds),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalNumberPicker(currentQuestionIndex, questions.size)
        Spacer(modifier = Modifier.height(12.dp))
        if (currentQuestion.requirement == null) {
            //  currentQuestion.question?.let { Text(it, fontSize = 16.sp) }
            Text(text = "")
        } else {
            Text(
                text = " ${currentQuestion.requirement}",
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        if (currentQuestion.text == null) {
            Text("", fontSize = 0.sp)
        } else {
            Box(
                modifier = Modifier.border(shape = RoundedCornerShape(6.dp), width = 0.1.dp, color = Color.Black)
                    .padding(vertical = 4.dp, horizontal = 6.dp),
            ) {

                Text(
                    text = "${currentQuestion.text}",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (currentQuestion.question == null) {
            Text("", fontSize = 0.sp)
        } else {
            Box(
                modifier = Modifier.border(shape = RoundedCornerShape(6.dp),width = 0.1.dp, color = Color.Black)
                    .padding(vertical = 4.dp, horizontal = 6.dp).fillMaxWidth(),
            ){
                Text(
                    text = "${currentQuestion.question}",
                    textAlign = TextAlign.Start,

                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (currentQuestion.type == null) {
            Text("", fontSize = 0.sp)
        } else {
            Text(
                text = "${currentQuestion.type}",
                textAlign = TextAlign.Start,
                fontSize = 16.sp
                , modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.border(shape = RoundedCornerShape(6.dp),width = 0.1.dp, color = Color.Black).padding(vertical = 4.dp, horizontal = 6.dp),) {

                Text(text = currentQuestion.anketa.toString(), fontSize = 16.sp, textAlign = TextAlign.Start, modifier = Modifier.align(Alignment.Center))

            }
        }



        // If the question has audio, show the audio controls
        currentQuestion.audio?.let { audioResId ->
            AudioPlayer(
                audioResId = audioResId,
                questionChangedTrigger = currentQuestionIndex // Trigger audio restart on question change
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        // Answer Buttons
        currentQuestion.answers?.forEach { answer ->
            Button(
                onClick = {
                    selectedAnswer = answer
                    if (answer == currentQuestion.correctAnswer) {
                        score += 1
                    }

                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex += 1
                    } else {
                        onQuizFinished(score, questions.size)
                    }
                },
                modifier = Modifier.width(265.dp).padding(2.dp).clip(
                    RoundedCornerShape(10.dp)
                ), shape = RectangleShape, colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor, contentColor = Color.White)
            ) {
                Text(text = answer, color = Color.White, fontSize = 14.sp)

            }}

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            currentQuestion.answersB?.forEach { answerB ->
                Box(
                    modifier = Modifier
                        .width(100.dp).height(78.dp).border(shape = RoundedCornerShape(6.dp),width = 0.1.dp, color = Color.Black)
                        .clickable {
                            selectedAnswerB = answerB
                            if (answerB == currentQuestion.correctB) {
                                score += 1
                            }

                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex += 1
                            } else {
                                onQuizFinished(score, questions.size)
                            }
                        }
                ) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Image(
                            painter = painterResource(id = answerB),
                            contentDescription = null,
                            modifier = Modifier.width(82.dp).height(55.dp)
                        )
                    }}
            }
        }
//Box(modifier = Modifier.border(width = 2.dp, color = Color.Black).padding(vertical = 4.dp, horizontal = 6.dp),) {
    }}
@Composable
fun HorizontalNumberPicker(currentQuestionIndex: Int, totalQuestions: Int) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(currentQuestionIndex) {
        gridState.animateScrollToItem(currentQuestionIndex)
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        state = gridState,
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp).width(26.dp)
    ) {
        items(totalQuestions) { index ->
            val number = index + 1
            val isSelected = number == currentQuestionIndex + 1

            // Dynamic colors for background and text
            val backgroundColor = if (isSelected) BackgroundColor else TextBackground
            val textColor = if (isSelected) Color.White else TextColorB
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .height(34.dp)
                    .width(26.dp).clip(RoundedCornerShape(6.dp))
                    .background(color = backgroundColor, shape = RectangleShape
                    ).border(shape = RoundedCornerShape(6.dp), width = 0.1.dp, color = Color.Black)
            ) {
                Text(
                    text = "$number",
                    color = textColor,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }

}



@Composable
fun AudioPlayer(audioResId: Int?, questionChangedTrigger: Int) {
    val context = LocalContext.current
    val mediaPlayerState = remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableIntStateOf(0) }
    var totalDuration by remember { mutableIntStateOf(0) }

    // Stop and release the MediaPlayer when the question changes or the user leaves the screen
    LaunchedEffect(key1 = questionChangedTrigger) {
        // Stop and release the previous audio when the question changes
        mediaPlayerState.value?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
        }
        mediaPlayerState.value = null
        isPlaying = false
        currentPosition = 0
        totalDuration = 0

        // Start the new audio if it exists
        if (audioResId != null) {
            val newPlayer = MediaPlayer.create(context, audioResId)
            mediaPlayerState.value = newPlayer
            totalDuration = newPlayer.duration
            newPlayer.start()
            isPlaying = true
        }
    }

    // Handle audio stopping when the composable is disposed (when user leaves the screen)
    DisposableEffect(key1 = Unit) {
        onDispose {
            mediaPlayerState.value?.let { player ->
                if (player.isPlaying) {
                    player.stop()
                }
                player.release()
            }
            mediaPlayerState.value = null
        }
    }

    // Update the current position periodically while playing
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (isPlaying && currentPosition < totalDuration) {
                delay(1000L)
                currentPosition = mediaPlayerState.value?.currentPosition ?: 0
            }
        }
    }

    // Audio player UI
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .width(265.dp)
            .background(LanguageBackground, shape = RectangleShape)
            .border(shape = RoundedCornerShape(6.dp), width = 0.1.dp, color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    mediaPlayerState.value?.let { player ->
                        if (isPlaying) {
                            player.pause()
                        } else {
                            player.start()
                        }
                        isPlaying = !isPlaying
                    }
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = if (isPlaying) R.drawable.play else R.drawable.pause),
                    contentDescription = if (isPlaying) "Pause Audio" else "Play Audio",
                    tint = BackgroundColor,
                    modifier = Modifier.padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                colors = SliderDefaults.colors(
                    thumbColor = BackgroundColor,
                    activeTrackColor = BackgroundColor,
                    activeTickColor = BackgroundColor,
                    inactiveTickColor = Color(0xFFBCEBFF),
                    inactiveTrackColor = Color(0xFFBCEBFF)
                ),
                value = currentPosition.toFloat(),
                onValueChange = { value ->
                    mediaPlayerState.value?.seekTo(value.toInt())
                    currentPosition = value.toInt()
                },
                modifier = Modifier.width(130.dp),
                valueRange = 0f..totalDuration.toFloat()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = formatTime(currentPosition), fontSize = 16.sp)
        }
    }
}

fun formatTime(ms: Int): String {
    val minutes = ms / 1000 / 60
    val seconds = (ms / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}