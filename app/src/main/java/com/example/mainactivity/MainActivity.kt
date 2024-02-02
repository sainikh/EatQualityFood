package com.example.mainactivity

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mainactivity.ui.theme.MainActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
//                MainScreen()
                DrinkCard("Output",rememberImagePainter(data = "https://www.thecocktaildb.com//images//media//drink//vwxrsw1478251483.jpg"))
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        SearchBar(onSearch = {})
        SpacerBarAfterSearch()
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

@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var searchText by remember {
        mutableStateOf(TextFieldValue("Search"))
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
            onValueChange = {
                searchText = it
                onSearch(it.text)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
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
fun DrinkCard(drinkName: String, thumbnail: Painter) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = thumbnail,
                contentDescription = "Drink Thumbnail",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = drinkName, style = MaterialTheme.typography.headlineLarge)
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
        DrinkCard("Output",rememberImagePainter(data = "https://www.thecocktaildb.com//images//media//drink//vwxrsw1478251483.jpg"))
    }
}