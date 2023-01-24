package com.omer.retrofitapp.service

import com.omer.retrofitapp.model.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {

    // GET, POST, UPDATE, DELETE gibi isteklerimizi yapacağımız metodları burada tanımlıyoruz.
    // @GET("endpoint") şeklinde tanımlıyoruz.
    // https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    // Base url'imiz--> https://raw.githubusercontent.com/
    // endpoint'imiz--> atilsamancioglu/K21-JSONDataSet/master/crypto.json
//https://raw.githubusercontent.com/omerfarukgzl/RetrofitApp/main/api/post2.json
    @GET("omerfarukgzl/RetrofitApp/main/post.json")
    fun getData(): Call<List<PostModel>>
}