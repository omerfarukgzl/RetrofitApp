package com.omer.retrofitapp.service

import com.omer.retrofitapp.model.Post
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {

    // Get Post
    //
    // api --> https://raw.githubusercontent.com/omerfarukgzl/RetrofitApp/main/api/post.json

    // Base Url --> https://raw.githubusercontent.com

    // value --> omerfarukgzl/RetrofitApp/main/api/post.json


   // Call Kullanarak --> fun getPost() : Call<List<Post>>
   // RxJava Kullanarak --> fun getPost() : Single<List<Post>>

    // Single bir defa veriyi çeker ve durur herhangi bir akışı yoktur devamında
    // Observable ise sürekli veri çeker ve devam eder. Veri değiştikçe değişir.
    // Single ile Observable arasındaki fark ise Single bir defa veriyi çeker ve durur herhangi bir akışı yoktur devamında
    // Observable ise sürekli veri çeker ve devam eder. Veri değiştikçe değişir.

    @GET("omerfarukgzl/RetrofitApp/main/api/post.json")
    fun getPost() : Call<List<Post>>


}