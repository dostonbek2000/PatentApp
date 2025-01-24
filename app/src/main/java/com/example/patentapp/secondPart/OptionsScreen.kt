package com.example.patentapp.secondPart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.patentapp.ui.theme.BackgroundColor
import com.example.patentapp.ui.theme.LanguageBackground
import com.example.patentapp.ui.theme.TextColor

@Composable
fun OptionsScreen(onCategorySelected: (Int) -> Unit,navController: NavController) {

    val categories = listOf("Варианты 1", "Варианты 2","Варианты 3","Варианты 4","Варианты 5")
   Column(modifier = Modifier.fillMaxSize().background(LanguageBackground), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
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
               "Patent",
               fontSize = 20.sp,
               textAlign = TextAlign.Start,
               color = Color.White,
               fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp)
           )
       }
       Column(
           modifier = Modifier.fillMaxWidth().padding(16.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(
               text = "Варианты",
               fontSize = 20.sp,
               color = TextColor,
               textAlign = TextAlign.Start,
               modifier = Modifier.align(Alignment.Start),
               fontWeight = FontWeight.Bold
           )
           Spacer(modifier = Modifier.height(16.dp))

           categories.forEachIndexed { index, category ->
               Button(
                   onClick = {
                       onCategorySelected(index + 1)
                   },
                   modifier = Modifier.fillMaxWidth().padding(4.dp).clip(
                       RoundedCornerShape(10.dp)
                   ).height(56.dp),
                   shape = RectangleShape,
                   colors = ButtonDefaults.buttonColors(
                       containerColor = BackgroundColor,
                       contentColor = Color.White
                   )
               ) {
                   Text(text = category, fontSize = 20.sp)
               }
           }


       }

       Button(
           onClick = {
               navController.navigate("support")
           },
           modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 40.dp).clip(
               RoundedCornerShape(10.dp)
           ).height(48.dp), shape = RectangleShape, colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor, contentColor = Color.White)
       ) {
           Text(text = "Поддержать", fontSize = 20.sp)
       }
       Spacer(modifier = Modifier.height(50.dp))
   }

}