package com.example.appsuperhero.data

import com.google.gson.annotations.SerializedName


class SuperheroesResponse(

    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<Superhero>

) {
}

class Superhero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("work") val work : Work

) {

}
class Biography (
    @SerializedName("full-name") val realName:String,
    @SerializedName("publisher") val publisher:String
) {

}
class Work (
    @SerializedName ("occupation") val occupation : String,
    @SerializedName ("base") val base : String
)

class Image(
    @SerializedName("url") val url: String
) {

}

