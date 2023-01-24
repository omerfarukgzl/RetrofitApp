package com.omer.retrofitapp.notes

class GeneralNotes {
    /*



    Öncelikle Retrofit ile çekeceğimiz api lerin modellerini oluşturduk.

    *** Data class yapmamızın sebebi constructor oluşturmamızı sağlamasıdır.

        data class CryptoModel(
            @SerializedName("currency")
            val currency: String,
            @SerializedName("price")
            val price: String
        )

   *** Daha sonra Service sınıfımızı oluşturduk.
        Service sınıfımızda retrofit ile api isteklerimizi yapacağımız metodları tanımladık.
        CryptoApi Interface Service i

        Burada @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") şeklinde tanımladığımız
        endpoint ile istek yapacağımızı belirttik.

            @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
            fun getData(): Call<List<CryptoModel>>

        ==> Sonucunda Call<List<CryptoModel>> döneceğini belirttik.




    *** Daha sonra BAseURL ve gelen veirlerin bir listeyle alınacak olana listeyi tanımladık
        private val BASE_URL = "https://raw.githubusercontent.com/"
         private var cryptoModels: ArrayList<CryptoModel>? = null
         ===>MainActivity.kt























    */
}