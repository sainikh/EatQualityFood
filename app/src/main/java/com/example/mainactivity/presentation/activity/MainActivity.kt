package com.example.mainactivity.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.example.mainactivity.Constants.Screen
import com.example.mainactivity.R
import com.example.mainactivity.domain.model.DrinkOnHome
import com.example.mainactivity.presentation.Details
import com.example.mainactivity.presentation.viewModel.BaseViewModel
import com.example.mainactivity.ui.theme.MainActivityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: BaseViewModel by viewModels()


        setContent {
            MainActivityTheme {
                navController = rememberNavController()
                val drinks by viewModel.drinksList.collectAsState()
                val loading by viewModel.loading.collectAsState()
                val failure by viewModel.failure.collectAsState()
                NavGroupSetUp(
                    navController as NavHostController,
                    viewModel,
                    drinks,
                    loading,
                    failure
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: BaseViewModel,
    drinks: List<DrinkOnHome>?,
    loading: Boolean?,
    failure: String?,
    navController: NavHostController
) {
    var position by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Header()
        SearchBar(viewModel, onSearch = {})
        SpacerBarAfterSearch()
        loading?.apply {
            CircularProgress(this)
        }
        drinks?.apply {
            DrinksList(this, navController, viewModel)
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        text = "Let's eat \nQuality food",
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
//        fontFamily = FontFamily(Font(id = R.font.figtree_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: BaseViewModel, modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var searchText by remember {
        mutableStateOf("")
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small)
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
        BasicTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
                viewModel.fetchData("search.php?s=" + searchText)
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.figtree_medium, FontWeight.Bold)),
                color = Color.Black
            ),
            decorationBox = { innerTextField ->
                if (searchText.isEmpty()) {
                    Text(
                        text = "Search",
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )
                }
                innerTextField()
            }
        )
    }

}

@Composable
fun SpacerBarAfterSearch(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Near Restaurant",
            modifier = Modifier.weight(10f),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "See All",
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun DrinkCard(
    drinkName: String,
    thumbnail: Painter,
    position: Int,
    onItemClick: (position: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable {
                onItemClick(position)
            },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = thumbnail,
                contentDescription = "Drink Thumbnail",
                modifier = Modifier
                    .size(250.dp)
                    .then(Modifier.clip(RoundedCornerShape(10.dp))),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.heightIn(10.dp))
            Text(
                text = drinkName,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily(
                    Font(R.font.figtree_medium, FontWeight.Light)
                )
            )
        }
    }
}

@Composable
fun NavGroupSetUp(
    navController: NavHostController,
    viewModel: BaseViewModel,
    drinks: List<DrinkOnHome>?,
    loading: Boolean?,
    failure: String?
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            MainScreen(
                viewModel = viewModel,
                drinks = drinks,
                loading = loading,
                failure = failure,
                navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            Details(it.arguments?.getInt("id")!!,viewModel)
        }
    }
}

@Composable
fun CircularProgress(loading: Boolean) {
    if (!loading) return

    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
    )
}


@Composable
fun DrinksList(drinks: List<DrinkOnHome>, navController: NavHostController, viewModel: BaseViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(drinks) { drink ->
            DrinkCard(
                drink.strDrink,
                rememberImagePainter(data = drink.strDrinkThumb),
                position = drinks.indexOf(drink),
                onItemClick = { position ->
                    navController.navigate(route = "Detail/$position")
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainActivityTheme {
//        Greeting()
//        SearchBar(onSearch = {})
//        SpacerBarAfterSearch()
//        MainScreen()
    }
}