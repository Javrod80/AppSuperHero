package com.example.appsuperhero.data

import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroesServiceApi {

    @GET("api/7252581128153666/search/{name}")
    suspend fun searchByName(@Path("name") query:String) : Response <SuperheroesResponse>


    }


