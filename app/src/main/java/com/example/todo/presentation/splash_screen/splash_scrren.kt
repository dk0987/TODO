package com.example.todo.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import com.example.todo.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.presentation.theme.Background
import com.example.todo.presentation.theme.Blue
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.example.todo.presentation.util.Screen
import kotlinx.coroutines.delay

@Composable
fun Splash_screen(
    navController: NavController
) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 1.1f,
            animationSpec = tween(
                durationMillis = 500,
                easing ={
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.popBackStack()
        navController.navigate(Screen.Home.screen)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Background),
        contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
       ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_note),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .scale(scale.value)
                        .size(130.dp),
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(Blue , 60.sp, fontWeight = FontWeight.Bold)){
                            append("T")
                        }
                        withStyle(style = SpanStyle(Color.White, 60.sp)){
                            append("o")
                        }
                        withStyle(style = SpanStyle(Blue, 60.sp, fontWeight = FontWeight.Bold)){
                            append("D")
                        }
                        withStyle(style = SpanStyle(Color.White , 60.sp)){
                            append("o")
                        }
                    } ,
                    textAlign = TextAlign.Center,
                    letterSpacing = 17.sp,
                    modifier = Modifier.scale(scale.value).padding(top = 5.dp)
                )
            }
        }
    }
}