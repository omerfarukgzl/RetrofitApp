package com.example.postapp_retrofit.notes

class RxJavaNotes {
    /*

    Birden fazla call olan yerlerde her call için hafıza yonetimi yapmak zorunda kalıyoruz.
    Bu yüzden RxJava kullanıyoruz.

    RxJava ile birden fazla call yapabiliyoruz.

    Call kullanmayacağız !!!
    @GET("omerfarukgzl/RetrofitApp/main/post.json")
    fun getData(): Observable<List<PostModel>>

    Observable ile gözlemlenebilir bir obke oluşturabiliyoruz eğer bir değişiklik olursa
    bize bildirir.

    ---> Retrofit build rxjava ya göre yazıldı ==> tek değişen yer addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PostApi::class.java)



    Dispoable nedir ?
    Bir gözlemi durdurmak için kullanılır . Disposable ile gözlemi durdurabiliriz.

        private var compositeDisposable: CompositeDisposable?=null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Rxjava disposable
            compositeDisposable = CompositeDisposable()
            ...

            }

  ----> CompositeDisposable tanımlaması
   compositeDisposable?.add(retrofit.getData()
     .subscribeOn(Schedulers.io())// asenkron çalıştırma -->  ana thread'i bloklamamak için veriye kaydolma abone olma işlemini arka tarafta yapacağız
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(this::handleResponse))

   --> Sheduler.io() --> veriyi çekme işlemini arka planda dinleme işlemini yapacak
   --> AndroidSchedulers.mainThread() --> main thread de veriyi işliyoruz
   --> hanldereesponse --> işlenen veriyi handle response fonk aktarıyor






























    * */
}