package az.appa.mobile.presentation.base

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

fun Navigator.push(route: String) {
    navigate(route)
}

fun Navigator.pop() {
    goBack()
}

fun Navigator.replace(route: String) {
    navigate(
        route,
        options = NavOptions(
            launchSingleTop = true,
            popUpTo = PopUpTo.First(inclusive = true)
        )
    )
}