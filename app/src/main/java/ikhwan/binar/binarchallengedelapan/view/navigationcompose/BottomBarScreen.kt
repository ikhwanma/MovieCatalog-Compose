package ikhwan.binar.binarchallengedelapan.view.navigationcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Popular: BottomBarScreen(
        route = "Popular",
        title = "Popular",
        icon = Icons.Default.Movie
    )

    object NowPlaying: BottomBarScreen(
        route = "now playing",
        title = "Now Playing",
        icon = Icons.Default.LocalMovies
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
