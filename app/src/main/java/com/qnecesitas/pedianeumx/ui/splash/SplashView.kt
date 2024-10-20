package com.qnecesitas.pedianeumx.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.qnecesitas.pedianeumx.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.qnecesitas.pedianeumx.navigation.Routes
import com.qnecesitas.pedianeumx.ui.theme.commonBackgroundGradient
import kotlinx.coroutines.delay


@Composable
fun SplashView(
    navController: NavHostController
){

    LaunchedEffect("") {
        delay(2000)
        navController.navigate(Routes.Camera.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = commonBackgroundGradient),
    ){
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )
    }

}