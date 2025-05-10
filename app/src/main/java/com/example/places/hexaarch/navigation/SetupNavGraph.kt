package com.example.places.hexaarch.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.places.hexaarch.navigation.AllDestinations.navController
import com.example.places.hexaarch.navigation.AllDestinations.viewModel
import com.example.places.hexaarch.screen.AnimatedSplashScreen
import com.example.places.hexaarch.screen.CartScreen
import com.example.places.hexaarch.screen.DashboardScreen
import com.example.places.hexaarch.screen.LoginScreen
import com.example.places.hexaarch.screen.PaymentScreen
import com.example.places.hexaarch.screen.SignUpScreen
import com.example.places.hexaarch.viewModel.DashboardViewModel

object AllDestinations {
    @SuppressLint("StaticFieldLeak")
    lateinit var navController : NavHostController
    lateinit var  viewModel: DashboardViewModel
}

@Composable
fun SetupNavGraph() {
    navController = rememberNavController()
    viewModel = viewModel ()

    NavHost(
        navController = navController,
        startDestination = Route.Splash.route
    ) {
        composable(route = Route.Splash.route) {
            AnimatedSplashScreen()
        }
        composable(route = Route.Login.route) {
            LoginScreen()
        }

        composable(route = Route.SignUp.route) {
            SignUpScreen()
        }
        composable(route = Route.Dashboard.route) {
            DashboardScreen()
        }
        composable(route = Route.CartScreen.route) {
            CartScreen()
        }

        composable(route = Route.PaymentScreen.route) {
            PaymentScreen(
                totalAmount = viewModel.getTotalAmount(),
                onPaymentConfirmed = {
                    // You could clear cart or navigate to home
                    navController.navigate("dashboard") {
                        popUpTo("cart") { inclusive = true }
                    }
                }
            )
        }
    }

}