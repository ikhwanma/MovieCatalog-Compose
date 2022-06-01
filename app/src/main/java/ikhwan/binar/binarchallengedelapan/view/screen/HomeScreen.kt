package ikhwan.binar.binarchallengedelapan.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ikhwan.binar.binarchallengedelapan.view.navigationcompose.BottomBarScreen
import ikhwan.binar.binarchallengedelapan.view.navigationcompose.BottomNavGraph
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelMovie
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModelMovie: ViewModelMovie,
    viewModelUser: ViewModelUser,
    viewModelState: ViewModelState,
    apiKey: String
) {

    viewModelMovie.getPopularMovie(apiKey)
    viewModelMovie.getNowPlayingMovie(apiKey)

    val listMovie = viewModelMovie.getPopularResponse
    val listNowPlayingMovie = viewModelMovie.getNowPlayingResponse

    val navController = rememberNavController()

    BottomNavGraph(
        navController = navController,
        listMovie = listMovie,
        listNowPlaying = listNowPlayingMovie
    )

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        BottomNavGraph(
            navController = navController,
            listMovie = listMovie,
            listNowPlaying = listNowPlayingMovie
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Popular,
        BottomBarScreen.NowPlaying,
        BottomBarScreen.Profile,
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