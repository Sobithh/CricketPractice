package com.mindorks.framework.taskpracticecricket.di

import com.mindorks.framework.taskpracticecricket.network.RequestInterface
import com.mindorks.framework.taskpracticecricket.repository.CricketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun bindAnalyticsService(): RequestInterface {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl("https://demo.sportz.io")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RequestInterface::class.java)  // class.java will create an object
        // of requestinterface that will give sme properties and object

    }

    @Provides
    @Singleton
    fun bindCricketRepository(RequestInterface: RequestInterface): CricketRepository {
        return CricketRepository(RequestInterface)
    }



}