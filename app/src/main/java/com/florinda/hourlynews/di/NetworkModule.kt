package com.florinda.hourlynews.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.florinda.common.Constants.CONNECT_TIMEOUT
import com.florinda.common.Constants.NEWS_API_KEY
import com.florinda.common.Constants.READ_TIMEOUT
import com.florinda.common.Constants.WRITE_TIMEOUT
import com.florinda.data.remote.NewsApiService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun okHttpClientProvider(): OkHttpClient  =
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(loggingInterceptorProvider())
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-Api-Key", NEWS_API_KEY)
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()


    private fun loggingInterceptorProvider(): HttpLoggingInterceptor  =
         HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }


}


