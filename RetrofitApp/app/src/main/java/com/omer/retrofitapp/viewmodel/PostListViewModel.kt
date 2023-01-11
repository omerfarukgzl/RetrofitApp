package com.omer.retrofitapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omer.retrofitapp.model.Post
import com.omer.retrofitapp.service.PostApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class PostListViewModel : ViewModel(){

    var posts = MutableLiveData<List<Post>>() // verileri tutacağımız değişken
    var postErrorMessage = MutableLiveData<Boolean>() // hata mesajı için
    var postLoading= MutableLiveData<Boolean>() // yükleniyor için

    private val postApiService = PostApiService()
    private val disposable = CompositeDisposable()// kullan at --> her bir istek disposable olacak. ve bu isteklerin açık kalmaması içinn disposable kullanılır.
    // ne zaman iş biterse sonlandırılabilir ==> RxJava


    fun refreshData()
    {
        getDataFromInternet()
    }

    fun getDataFromInternet()
    {
        // veriler yüklenirken progress barı gorelim
        postLoading.value=true


        postApiService.getData()
            .enqueue(object : retrofit2.Callback<List<Post>>{
                override fun onResponse(
                    call: retrofit2.Call<List<Post>>,
                    response: retrofit2.Response<List<Post>>
                ) {
                    // veriler yüklendiğinde progress barı kaldıralım
                    postLoading.value=false
                    Log.d("!!!!!onResponse",response.body().toString())

                    if(response.isSuccessful)
                    {
                        posts.value=response.body()
                        postErrorMessage.value=false
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<Post>>, t: Throwable) {
                    // veriler yüklendiğinde progress barı kaldıralım
                    postLoading.value=false

                    postErrorMessage.value=true
                }

            })


        /*disposable.add(
            postApiService.getData() //veriler değiştimi gozlemlendimi arka planda new thred ile yapmalıyuız
                .subscribeOn(Schedulers.newThread())//--> aseknron bir şekilde single observable ' ye kayıt olur.
                .observeOn(AndroidSchedulers.mainThread())//gozlemleme işini main thread de yapıyoruz --> kullanıcıya gpstereceğimiz yerde
                .subscribeWith(object: DisposableSingleObserver<List<Post>>()
                {
                    // veriler değiştiğinde ne olacak
                    override fun onSuccess(t: List<Post>) {
                        posts.value=t
                        postLoading.value=false
                        postErrorMessage.value=false
                    }

                    override fun onError(e: Throwable) {
                        postLoading.value=false
                        postErrorMessage.value=true
                        e.printStackTrace()// hata alırsak logcat de gorme
                    }

                })
        )*/
    }









































    /*    fun refreshData()
    {
        val banana = Post("https://www.ufuktarim.com/imaj/blog/sera-muz-yetistiriciligi.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Banana. Banana is Yellow",
            "Omer Faruk Guzel")

        val apple = Post("https://cdnd-tr.ceptesok.com/product/1000x1000/ce378_Elma_Granny_Kg_.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Apple. Apple is Yellow",
            "Taha Yasin Guzel")


        val orange = Post("https://cdn.yemek.com/mncrop/940/625/uploads/2015/04/portakal.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Orange. Orange is Yellow",
            "Muhammed Furkan Guzel")


        val strawbarry = Post("https://cdn1.ntv.com.tr/gorsel/J3JsXu1BlkKrNcoJg_pk-A.jpg?width=1000&mode=both&scale=both&v=1652861149667",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Strawbarry. Strawbarry is Yellow",
            "Omer Guzel")

        // oluşturulan besinleri array listde saklama - sonradan ekelme çıkarma yapılabilir
        val postlist= arrayListOf<Post>(banana,strawbarry,apple,orange)

        // besinler MutableLiveData değişkenine besinlistesini atama
        posts.value=postlist
        postErrorMessage.value=false
        postLoading.value=false
    }
    fun refreshData2()
    {
        val avocado = Post("https://www.pritikin.com/wp-content/uploads/2022/09/are-avocados-healthy.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Avocado. Avocado is green",
            "Ebubekir Guzel")


        val charry = Post("https://www.medikalakademi.com.tr/wp-content/uploads/2019/05/kiraz-meyve-14.jpg.webp",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Charry. Charry is Red",
            "Omer Guzel")

        val mandarin = Post("https://www.acibadem.com.tr/hayat/Images/YayinMakaleler/mandalinanin-kabugunda-bile-saglik-var_6960_1.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Mandarin. Mandarin is Orange",
            "Osman Guzel")

        val lemon = Post("https://w0.peakpx.com/wallpaper/969/297/HD-wallpaper-yellow-lemons-with-green-leaves-in-yellow-background-yellow-background.jpg",
            "https://cdn-icons-png.flaticon.com/512/159/159113.png?w=740&t=st=1672598461~exp=1672599061~hmac=7b10e10a22a7bec7276e6d2a429a554de035f3209e595e93a3e5ebc463a4434b",
            "This is Lemon. Lemon is Yellow",
            "Ali Guzel")

        // oluşturulan besinleri array listde saklama - sonradan ekelme çıkarma yapılabilir
        val postlist= arrayListOf<Post>(avocado,charry,mandarin,lemon)

        // besinler MutableLiveData değişkenine besinlistesini atama
        posts.value=postlist
        postErrorMessage.value=false
        postLoading.value=false
    }
    */
}