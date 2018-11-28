package net.digitapp.marketcompare.service

import net.digitapp.marketcompare.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    var networkRequestInstance: Retrofit? = null

    fun getNetworkInstance(): Retrofit? {
        val okhttp = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.DEBUG) {
            okhttp.addInterceptor(logging)
        }

        if (networkRequestInstance == null)
            networkRequestInstance = Retrofit.Builder()
                .baseUrl(Companion.BASED_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).client(okhttp.build()).build()
        return networkRequestInstance
    }

    companion object {
        private const val BASED_URL = "https://onlinestorecompare.herokuapp.com/"
    }
}