package com.example.appsuperhero.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroesServiceApi {

    @GET("api/7252591128153666/search/{name}")
    suspend fun searchByName(@Path("name") query:String) : Response <SuperheroesResponse>






    }



