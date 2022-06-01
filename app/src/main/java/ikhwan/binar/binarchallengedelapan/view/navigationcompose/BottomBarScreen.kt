package ikhwan.binar.binarchallengedelapan.view.navigationcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Popular: BottomBarScreen(
        route = "popular",
        title = "Popular",
        icon = Icons.Default.Movie
    )

    object NowPlaying: BottomBarScreen(
        route = "now",
        title = "Now Playing",
        icon = Icons.Default.Movie
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
