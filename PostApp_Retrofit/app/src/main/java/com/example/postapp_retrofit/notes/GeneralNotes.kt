package com.omer.retrofitapp.notes

class GeneralNotes {


    /*



  **** Mainifest.xml de internet izni vermek için
        <uses-permission android:name="android.permission.INTERNET" />


     Retrofit ile çekeceğimiz api lerin modellerini oluşturduk.

    *** Data class yapmamızın sebebi constructor oluşturmamızı sağlamasıdır.

        data class PostModel(
            @SerializedName("postImage")
            val postImage: String,
            @SerializedName("userImage")
            val userImage: String,
            @SerializedName("postStatement")
            val postStatement: String,
            @SerializedName("userName")
            val userName: String


        )

   *** Daha sonra Service sınıfımızı oluşturduk.
        Service sınıfımızda retrofit ile api isteklerimizi yapacağımız metodları tanımladık.
        CryptoApi Interface Service i

        Burada @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") şeklinde tanımladığımız
        endpoint ile istek yapacağımızı belirttik.

         @GET("omerfarukgzl/RetrofitApp/main/post.json")
        fun getData(): Call<List<PostModel>>

        ==> Sonucunda Call<List<CryptoModel>> döneceğini belirttik.



    *** Daha sonra BAseURL ve gelen veirlerin bir listeyle alınacak olana listeyi tanımladık
        private val BASE_URL = "https://raw.githubusercontent.com/"
         private var cryptoModels: ArrayList<CryptoModel>? = null
         ===>MainActivity.kt

      **Activty ile recycler adapter arasında bağlantı kurduk.
           private var binding: ActivityMainBinding ? = null

           private lateinit var recyclerView: RecyclerView
           private lateinit var adapter: PostRecyclerAdapter

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                // layout adapter'ı tanımladık
                //adapter ile bağlama

                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this)

            }



   ****** Daha sonra Retrofit ile verileri çekiyoruz

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

    */
}