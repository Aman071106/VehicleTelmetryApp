package com.example.vehicletelemetry.retrofitModule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetroObject{

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://vehicletelemetryapp-default-rtdb.firebaseio.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun FirebaseAPI(retrofit: Retrofit):RequestInterface{
        return retrofit.create(RequestInterface::class.java)
    }


}