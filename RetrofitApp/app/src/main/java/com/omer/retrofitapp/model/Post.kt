package com.omer.retrofitapp.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("postImage")
    var postImage:String?,
    @SerializedName("userImage")
    var userImage : String?,
    @SerializedName("postStatement")
    var postStatement:String?,
    @SerializedName("userName")
    var userName :String?
){

}