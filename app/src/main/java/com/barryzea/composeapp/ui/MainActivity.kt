package com.barryzea.composeapp

import android.os.Bundle
import android.view.animation.OvershootInterpolator

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barryzea.composeapp.ui.CharactersList
import com.barryzea.composeapp.ui.MainScreen


import com.barryzea.composeapp.ui.theme.ComposeAppTheme
import com.barryzea.composeapp.ui.DetailScreen
import com.barryzea.composeapp.ui.theme.Pink40
import com.barryzea.composeapp.ui.theme.Purple40
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController= rememberNavController()

            ComposeAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    SetUpNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SetUpNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "splash_screen"){

        composable(route="splash_screen"){
            SplashScreen(navController = navController)
        }
        composable(route = "main_screen"){
            MainScreen(navController=navController)
        }
        composable(route="detail_screen/{detailId}",
        arguments = listOf(navArgument("detailId"){type= NavType.StringType})){
            it.arguments?.getString("detailId")?.let{id->
                DetailScreen(navController = navController,id)
            }
        }

    }
}
@Composable
fun SplashScreen(navController:NavController){
    val scale = remember{androidx.compose.animation.core.Animatable(0f)}
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {OvershootInterpolator(4f).getInterpolation(it)}
            )

        )
        delay(2000L)
        navController.navigate("main_screen"){
            launchSingleTop=true
        }
    }
    Box(contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(
                    Purple40, Pink40
                )
            )
        )){
        Image(painter= painterResource(id = R.drawable.starwars_placeholder),
        contentDescription = "",
        modifier=Modifier.scale(scale.value))
    }

}