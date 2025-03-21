package com.example.appsuperhero.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appsuperhero.R
import com.example.appsuperhero.adapters.SuperheroAdapter
import com.example.appsuperhero.data.Superhero
import com.example.appsuperhero.data.SuperheroesServiceApi
import com.example.appsuperhero.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.widget.SearchView.OnQueryTextListener as OnQueryTextListener1

class MainActivity : AppCompatActivity(), OnQueryTextListener1 {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperheroAdapter
    private var superheroList : List <Superhero> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(this)

        //binding.iniImage.setImageResource()


        initRecycledView()

    }

    private fun initRecycledView() {

        adapter = SuperheroAdapter(superheroList){
            onItemClickListener(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

    }


    private fun onItemClickListener(position: Int) {


        val superhero: Superhero = superheroList[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("SUPERHERO_ID", superhero.id)
        intent.putExtra("SUPERHERO_NAME" , superhero.name)
        intent.putExtra("SUPERHERO_IMAGE" , superhero.image.url)
        intent.putExtra("PUBLISHER" , superhero.biography.publisher)
        intent.putExtra("REAL_NAME", superhero.biography.realName)
        intent.putExtra("OCCUPATION", superhero.work.occupation)
        intent.putExtra("BASE",superhero.work.base)




        startActivity(intent)


    }

    private fun searchSuperheroes(query: String) {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call: SuperheroesServiceApi = retrofit.create(SuperheroesServiceApi::class.java)



        CoroutineScope(Dispatchers.IO).launch {
            val response = call.searchByName(query)

            runOnUiThread {
                if (response.body() != null) {
                    Log.i("HTTP", "Respuesta correcta")
                    superheroList = response.body()?.results.orEmpty()
                    adapter.updateItems(superheroList)



                } else {
                    Log.i("HTTP", "Respuesta incorrecta")
                    showError()


                }
                hideKeyboard()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean { // listener
        if (!query.isNullOrEmpty()) {
            searchSuperheroes(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //barra
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }





}
