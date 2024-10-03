package com.example.dogimagelibrary

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DogImageFetcher(context: Context){
    private val api: DogApi = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApi::class.java)

    suspend fun getImage(): String {
        return api.getRandomImage().message
    }

    suspend fun getImages(number: Int): List<String> {
        return List(number) { getImage() }
    }
}

interface DogApi {
    @GET("breeds/image/random")
    suspend fun getRandomImage(): DogResponse
}

data class DogResponse(val message: String, val status: String)
