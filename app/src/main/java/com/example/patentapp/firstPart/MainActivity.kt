package com.example.patentapp.firstPart

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.example.patentapp.data.getQuestionsForCategory
import com.example.patentapp.secondPart.OptionsScreen
import com.example.patentapp.secondPart.QuestionsScreen
import com.example.patentapp.secondPart.QuizApp
import com.example.patentapp.secondPart.ResultsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(context = applicationContext)

        }
    }
}

@Composable
fun AppNavigation(context: Context) {
    val navController = rememberNavController()

    // Check if the user has completed the onboarding process
    val hasCompletedOnboarding = remember { mutableStateOf(hasCompletedOnboarding(context)) }

    // Use NavHost to decide which navigation flow to show
    NavHost(
        navController = navController,
        startDestination = if (hasCompletedOnboarding.value) "main" else "welcome"
    ) {
        // First Navigation Flow (Welcome, Language, and Onboarding)
        composable("welcome") {
            WelcomeScreen(onStart = {
                navController.navigate("onboarding")
            })
        }



        composable("onboarding") {
            OnBoardingScreen(onFinish = {
                // Set the flag indicating onboarding is complete
                hasCompletedOnboarding.value = true
                setOnboardingCompleted(context, true)
                navController.navigate("main")
            })
        }

        // Main Screen (After First-Time Setup)
        composable("main") {
            QuizApp() // Main content screen for the quiz
        }

        // Other Screens for the quiz
        composable("category") {
            OptionsScreen(
                onCategorySelected = { categoryId ->
                    navController.navigate("question/$categoryId")
                },
                navController = navController
            )
        }

        composable("question/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toInt() ?: 1
            val questions = getQuestionsForCategory(categoryId)
            QuestionsScreen(
                categoryId = categoryId,
                onQuizFinished = { score, totalQuestions ->
                    navController.navigate("result/$score/$totalQuestions")
                },
                questions = questions
            )
        }

        composable("result/{score}/{totalQuestions}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 0
            ResultsScreen(score = score, totalQuestions = totalQuestions, onRestart = {
                navController.popBackStack("category", false)
            })
        }
    }
}

fun hasCompletedOnboarding(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("hasCompletedOnboarding", false)
}

fun setOnboardingCompleted(context: Context, value: Boolean) {
    val sharedPreferences = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("hasCompletedOnboarding", value).apply()
}
