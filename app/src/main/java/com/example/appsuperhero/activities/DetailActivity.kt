package com.example.appsuperhero.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appsuperhero.R
import com.example.appsuperhero.data.Superhero
import com.example.appsuperhero.data.SuperheroesServiceApi
import com.example.appsuperhero.databinding.DetailActivityBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var superhero : Superhero


    private var superheroId: String? = null
    private var publisher : String = "PUBLISHER"
    private var realname :String? = "REAL_NAME"

    private lateinit var binding: DetailActivityBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        superheroId = intent.getStringExtra("SUPERHERO_ID")
        realname =intent.getStringExtra("REAL_NAME")
        publisher = intent.getStringExtra("PUBLISHER")!!

        detailSuperhero(superheroId!!)

        initView()




    }

    private fun initView () {
        Picasso.get().load(superhero.image.url).into(binding.superheroImageView)
        binding.textBiography.text


    }

    private fun detailSuperhero (id: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call: SuperheroesServiceApi = retrofit.create(SuperheroesServiceApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = call.superheroId(id)

            runOnUiThread {
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta")
                    superhero = response.body()!!
                }else {
                    Log.i("HTTP","respuesta incorrecta" )
                }

            }






        }


    }










}


