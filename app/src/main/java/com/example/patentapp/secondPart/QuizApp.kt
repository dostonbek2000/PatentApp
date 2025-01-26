package com.example.patentapp.secondPart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patentapp.R
import com.example.patentapp.data.getQuestionsForCategory

@Composable
fun QuizApp() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf("category",  "result")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "category",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("category") {
                OptionsScreen(onCategorySelected = { categoryId ->
                    navController.navigate("question/$categoryId")
                },navController=navController)
            }
            composable("support") {
                SupportScreen(
                    cardA = "+79858934045",
                    cardB = "4073 4200 8390 9121",
                    nameA = "Нурулла Кенжахўжаев",
                    nameB = "Nurulla Kenjaxo'jayev"
                )
            }
            composable("question/{categoryId}") { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")?.toInt() ?: 1
                QuestionsScreen(
                    categoryId = categoryId,
                    onQuizFinished = { score, totalQuestions ->
                        navController.navigate("result/$score/$totalQuestions")
                    },
                    questions = getQuestionsForCategory(categoryId)
                )
            }
            composable("result/{score}/{totalQuestions}") { backStackEntry ->
                val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
                val totalQuestions =
                    backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 0
                ResultsScreen(score = score, totalQuestions = totalQuestions, onRestart = {
                    navController.popBackStack("category", false)
                },navController)
            }
        }
    }
}