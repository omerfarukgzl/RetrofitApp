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
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var postModels: ArrayList<PostModel>? = null

    // Main Activty Binding
    private var binding: ActivityMainBinding ? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostRecyclerAdapter

    // Rxjava için
        // Disposable
        private var compositeDisposable: CompositeDisposable?=null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Rxjava disposable
            compositeDisposable = CompositeDisposable()

            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            loadData()
            // layout adapter'ı tanımladık
            //adapter ile bağlama

        }

    // retrofit ile verileri çekiyoruz
    private fun loadData() {


        // RxJava ile

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PostApi::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())// asenkron çalıştırma -->  ana thread'i bloklamamak için veriye kaydolma abone olma işlemini arka tarafta yapacağız
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
            // Sheduler.io() --> veriyi çekme işlemini arka planda dinleme işlemini yapacak
            // AndroidSchedulers.mainThread() --> main thread de veriyi işliyoruz
            // hanldereesponse --> işlenen veriyi handle response fonk aktarıyor





        // Call ile


        /*

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
                      *//*  for (cryptoModel: PostModel in postModels!!) {
                            println(cryptoModel.postStatement)
                            println(cryptoModel.userName)
                        }*//*
                    }
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })*/


    }
    private fun handleResponse(postList: List<PostModel>){
        postModels = ArrayList(postList)

        adapter = PostRecyclerAdapter(postModels!!)
        recyclerView.adapter = adapter
    }
}