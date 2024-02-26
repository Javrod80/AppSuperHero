package com.example.appsuperhero.activities

import android.app.DownloadManager.Query
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsuperhero.R
import com.example.appsuperhero.adapters.SuperheroAdapter
import com.example.appsuperhero.data.SuperheroesServiceApi
import com.example.appsuperhero.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperheroAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchText = binding.searchEditText.text.toString()
            searchSuperheroes(searchText)

        }
        adapter = SuperheroAdapter()
        binding.recyclerView.adapter =adapter
        binding.recyclerView.layoutManager = LinearLayoutManager (this)


    }

    private fun searchSuperheroes (query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : SuperheroesServiceApi = retrofit.create(SuperheroesServiceApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {


            val response = service.searchByName(String())


            runOnUiThread {

                if (response != null) {
                    Log.i("HTTP", "respuesta correcta ")
                    adapter.updateItems(response.body()?.results)

                    }else  {
                    Log.i("HTTP","respuesta incorrecta  ")



                }




            }
        }


    }
}