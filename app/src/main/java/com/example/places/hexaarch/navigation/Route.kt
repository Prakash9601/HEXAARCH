package com.example.places.hexaarch.navigation

import com.caredom.family.caredomfamily.utils.Const.CARTSCREEN
import com.caredom.family.caredomfamily.utils.Const.DASHBOARD
import com.caredom.family.caredomfamily.utils.Const.LOGIN
import com.caredom.family.caredomfamily.utils.Const.PAYMENT
import com.caredom.family.caredomfamily.utils.Const.SIGNUP
import com.caredom.family.caredomfamily.utils.Const.SPLASH


sealed class Route(val route: String) {
    object Login: Route(LOGIN)
    object Splash: Route(SPLASH)
    object Dashboard: Route(DASHBOARD)
    object SignUp: Route(SIGNUP)
    object CartScreen: Route(CARTSCREEN)
    object PaymentScreen: Route(PAYMENT)
}
