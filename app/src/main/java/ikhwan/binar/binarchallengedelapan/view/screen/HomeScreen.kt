package ikhwan.binar.binarchallengedelapan.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.view.navigationcompose.BottomBarScreen
import ikhwan.binar.binarchallengedelapan.view.navigationcompose.BottomNavGraph
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@Composable
fun HomeScreen(

    viewModelUser: ViewModelUser,
    listPopular: MutableList<Result>,
    listNowPlaying: MutableList<Result>
) {

    var user = GetUserResponseItem("", "", "", "", "", "", "")
    val emailState = viewModelUser.getIdState
    if (emailState != ""){
        viewModelUser.getUserId(emailState)
        val users = viewModelUser.getUserIdResponse
        if (users.isNotEmpty()){
            user = users[0]
        }
    }

    val navController = rememberNavController()

    BottomNavGraph(
        navController = navController,
        listMovie = listPopular,
        listNowPlaying = listNowPlaying,
        name = user.username,
        viewModelUser = viewModelUser
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        BottomNavGraph(
            navController = navController,
            listMovie = listPopular,
            listNowPlaying = listNowPlaying,
            name = user.username,
            viewModelUser = viewModelUser
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Popular,
        BottomBarScreen.NowPlaying,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")
        },
        selected = currentDestination!!.hierarchy.any {
            it.route == screen.route
        },
        onClick = {
            navController.navigate(screen.route)
        },
        modifier = Modifier.weight(0.75f)
    )
}