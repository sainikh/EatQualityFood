package com.example.mainactivity.utils

import com.example.mainactivity.domain.model.Drink
import kotlin.reflect.full.memberProperties


fun getInstructions(drink: Drink, allKeys: List<String>): List<String> {
    val inputKeys = allKeys.filter {
        it.contains("strInstructions", ignoreCase = true)
    }
    var output = inputKeys.map { key ->
        drink.javaClass.kotlin.memberProperties.firstOrNull() {
            it.name == key
        }?.get(drink).toString()
    }
    output = output.filter { it != "null" }
    return  output.mapIndexed { index, element ->
        inputKeys[index] + " : " + element
    }


}

fun getIngredients(drink: Drink, allKeys: List<String>): List<String> {
    val inputKeys = allKeys.filter {
        it.contains("strIngredient", ignoreCase = true)
    }
    val sortedKeys = inputKeys.sortedWith(compareBy { it.substringAfter("strIngredient").toInt() })
    var output = sortedKeys.map { key ->
        drink.javaClass.kotlin.memberProperties.firstOrNull() {
            it.name == key
        }?.get(drink).toString()
    }
    output = output.filter { it != "null" }
    return output.mapIndexed { index, element ->
        inputKeys[index] + " : " + element
    }
}

fun getMeasures(drink: Drink, allKeys: List<String>): List<String> {
    val inputKeys = allKeys.filter {
        it.contains("strMeasure", ignoreCase = true)
    }
    val sortedKeys = inputKeys.sortedWith(compareBy { it.substringAfter("strMeasure").toInt() })
    var output = sortedKeys.map { key ->
        drink.javaClass.kotlin.memberProperties.firstOrNull() {
            it.name == key
        }?.get(drink).toString()
    }
    output = output.filter { it != "null" }
    return output.mapIndexed { index, element ->
        inputKeys[index] + " : " + element
    }
}