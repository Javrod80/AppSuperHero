package com.example.appsuperhero.data

import android.media.Image
import com.google.gson.annotations.SerializedName

data class SuperheroesResponse(

    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<SuperHero>


) {

}

class SuperHero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName ("image") val image :Image

){

}
    class Image (
        @SerializedName ("url") val url : String
    ){

}
