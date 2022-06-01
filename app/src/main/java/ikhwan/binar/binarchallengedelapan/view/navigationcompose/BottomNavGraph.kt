package ikhwan.binar.binarchallengedelapan.view.navigationcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ikhwan.binar.binarchallengedelapan.view.screen.NowPlaying
import ikhwan.binar.binarchallengedelapan.view.screen.PopularScreen
import ikhwan.binar.binarchallengedelapan.view.screen.ProfileScreen
import ikhwan.binar.binarchallengelima.model.popularmovie.ResultMovie

@ExperimentalFoundationApi
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    listMovie : List<ResultMovie>,
    listNowPlaying : List<ResultMovie>
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Popular.route
    ) {
        composable(route = BottomBarScreen.Popular.route) {
            PopularScreen(listMovie = listMovie)
        }
        composable(route = BottomBarScreen.NowPlaying.route) {
            NowPlaying(listMovie = listNowPlaying)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}