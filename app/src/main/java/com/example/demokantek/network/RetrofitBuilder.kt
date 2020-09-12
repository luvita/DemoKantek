package com.example.demokantek.network

import android.os.Build
import com.example.demokantek.repository.LoginRepository
import com.example.demokantek.viewmodel.loginViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


val mainModule = module {

    single { LoginRepository(get()) }

    single { RetrofitBuilder.apiService }

    viewModel { loginViewModel(get()) }

}

object RetrofitBuilder {
    private val BASE_URL = "http://yelp-test.kennjdemo.com/api/v1/"

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a Custom Interceptor to apply Headers application wide
    val headerInterceptor = object: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            var request = chain.request()

            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()

            val response = chain.proceed(request)
            return response
        }
    }

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)

    // Create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Create Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    val apiService: NetWorkApi = retrofit.create(NetWorkApi::class.java)

//    fun provideMovieAPI(): Apis {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(Apis::class.java)
//    }
}