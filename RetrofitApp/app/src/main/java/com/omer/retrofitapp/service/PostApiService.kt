package com.omer.retrofitapp.service


import com.omer.retrofitapp.model.Post
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.RxThreadFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostApiService {

    private val BASE_URL="https://raw.githubusercontent.com/"


    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApi::class.java)

/*

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // json formatını model 'e çevireceğiz
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PostApi::class.java)
*/

    // bir tane fonksiyon yazalım ve tüm api verilerini alabilelim
    fun getData() : Call<List<Post>>
    {
        return retrofit.getPost()
    }



}