package com.example.mainactivity.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mainactivity.domain.model.Drink
import com.example.mainactivity.presentation.viewModel.BaseViewModel
import com.example.mainactivity.utils.getIngredients
import com.example.mainactivity.utils.getInstructions
import com.example.mainactivity.utils.getMeasures
import kotlin.reflect.full.memberProperties


@Composable
fun Details(position: Int, viewModel: BaseViewModel) {
    val drinks by viewModel.actualDrink.collectAsState()
    val drink = drinks!![position]
    val allKeys = getListOfAllKeys(drink)
    val instructionList = getInstructions(drink, allKeys)
    val ingredientList = getIngredients(drink, allKeys)
    val measurementList = getMeasures(drink, allKeys)
    Column {
        Image(
            painter = rememberImagePainter(data = drink.strDrinkThumb),
            modifier = Modifier
                .padding(10.dp)
                .then(Modifier.clip(RoundedCornerShape(10.dp))),
            contentDescription = "SimpleImage"
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(instructionList) {
                Text(text = it, modifier = Modifier.padding(20.dp))
            }
            items(ingredientList) {
                Text(text = it, modifier = Modifier.padding(20.dp))
            }
            items(measurementList) {
                Text(text = it, modifier = Modifier.padding(20.dp))
            }
            item(drink.strCategory) {
                Text(text = "Category :" + drink.strCategory, modifier = Modifier.padding(20.dp))
            }

        }

    }


}

fun getListOfAllKeys(drink: Drink): List<String> {
    return drink::class.memberProperties.map {
        it.name
    }
}


@Preview
@Composable
fun GetPreview() {

}