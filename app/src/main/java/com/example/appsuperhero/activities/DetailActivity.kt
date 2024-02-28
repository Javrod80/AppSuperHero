package com.example.appsuperhero.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
    private var occupation : String = "OCCUPATION"
    private var base : String = "BASE"

    private lateinit var binding: DetailActivityBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        superheroId = intent.getStringExtra("SUPERHERO_ID")
        realname =intent.getStringExtra("REAL_NAME")
        publisher = intent.getStringExtra("PUBLISHER")!!
        occupation= intent.getStringExtra("OCCUPATION")!!
        base = intent.getStringExtra("BASE")!!


        detailSuperhero(superheroId!!)






    }



    private fun loadData() {
        Picasso.get().load(superhero.image.url).into(binding.imageSH)
        binding.textRealName.text = superhero.biography.realName
        binding.detalle.text = superhero.biography.publisher
        binding.occupation.text = superhero.work.occupation
        binding.baseOperation.text = superhero.work.base
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
                    loadData()
                }else {
                    Log.i("HTTP","respuesta incorrecta" )
                }

            }






        }


    }










}


