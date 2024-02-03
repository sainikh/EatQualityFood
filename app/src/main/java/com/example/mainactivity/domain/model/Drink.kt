package com.example.mainactivity.domain.model

import com.google.gson.annotations.SerializedName

data class Drink(
    val dateModified: String,
    val idDrink: String,
    val strAlcoholic: String,
    @SerializedName("strCategory")
    val Category: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkAlternate: Any,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String,
    val strImageAttribution: String,
    val strImageSource: String,
    @SerializedName("strIngredient1")
    val Ingredient1: String,
    @SerializedName("strIngredient10")
    val Ingredient10: Any,
    @SerializedName("strIngredient11")
    val Ingredient11: Any,
    @SerializedName("strIngredient12")
    val Ingredient12: Any,
    @SerializedName("strIngredient13")
    val Ingredient13: Any,
    @SerializedName("strIngredient14")
    val Ingredient14: Any,
    @SerializedName("strIngredient15")
    val Ingredient15: Any,
    @SerializedName("strIngredient2")
    val Ingredient2: String,
    @SerializedName("strIngredient3")
    val Ingredient3: String,
    @SerializedName("strIngredient4")
    val Ingredient4: String,
    @SerializedName("strIngredient5")
    val Ingredient5: String,
    @SerializedName("strIngredient6")
    val Ingredient6: String,
    @SerializedName("strIngredient7")
    val Ingredient7: String,
    @SerializedName("strIngredient8")
    val Ingredient8: Any,
    @SerializedName("strIngredient9")
    val Ingredient9: Any,
    @SerializedName("Instructions")
    val Instructions: String,
    @SerializedName("strInstructionsDE")
    val InstructionsDE: String,
    @SerializedName("strInstructionsES")
    val InstructionsES: Any,
    @SerializedName("strInstructionsFR")
    val InstructionsFR: Any,
    @SerializedName("strInstructionsIT")
    val InstructionsIT: String,
    @SerializedName("strInstructionsZH-HANS")
    val InstructionsHANS: Any,
    @SerializedName("strInstructionsZH-HANT")
    val InstructionsHANT: Any,
    @SerializedName("Measure1")
    val Measure1: String,
    @SerializedName("strMeasure10")
    val Measure10: Any,
    @SerializedName("strMeasure11")
    val Measure11: Any,
    @SerializedName("strMeasure12")
    val Measure12: Any,
    @SerializedName("strMeasure13")
    val Measure13: Any,
    @SerializedName("strMeasure14")
    val Measure14: Any,
    @SerializedName("strMeasure15")
    val Measure15: Any,
    @SerializedName("strMeasure2")
    val Measure2: String,
    @SerializedName("strMeasure3")
    val Measure3: String,
    @SerializedName("strMeasure4")
    val Measure4: String,
    @SerializedName("strMeasure5")
    val Measure5: String,
    @SerializedName("strMeasure6")
    val Measure6: String,
    @SerializedName("strMeasure7")
    val Measure7: String,
    @SerializedName("strMeasure8")
    val Measure8: Any,
    @SerializedName("strMeasure9")
    val Measure9: Any,
    val strTags: String,
    val strVideo: Any
)