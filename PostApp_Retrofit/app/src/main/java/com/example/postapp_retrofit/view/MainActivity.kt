package com.example.postapp_retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omer.retrofitapp.R
import com.omer.retrofitapp.adapter.PostRecyclerAdapter
import com.omer.retrofitapp.databinding.ActivityMainBinding
import com.omer.retrofitapp.model.PostModel
import com.omer.retrofitapp.service.PostApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var postModels: ArrayList<PostModel>? = null

    // Main Activty Binding
    private var binding: ActivityMainBinding ? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        // layout adapter'ı tanımladık
        //adapter ile bağlama



        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    // retrofit ile verileri çekiyoruz
    private fun loadData() {
        // Retrofit ile istek yapacağımız sınıfımızı oluşturuyoruz.
        // Bu sınıfımızı oluştururken BASE_URL'imizi veriyoruz.
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postModels = ArrayList(it)

                        adapter = PostRecyclerAdapter(postModels!!)
                        recyclerView.adapter = adapter
                      /*  for (cryptoModel: PostModel in postModels!!) {
                            println(cryptoModel.postStatement)
                            println(cryptoModel.userName)
                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })


    }
}