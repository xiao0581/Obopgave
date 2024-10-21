package com.example.obopgave.Repository

import retrofit2.Call
import retrofit2.http.*
import com.example.obopgave.ViewModel.Beer

interface BeerService {

        @GET("beers")
        fun getAllBeers(): Call<List<Beer>>

        @GET("beers/{BeerId}")
        fun getBeerById(@Path("BeerId") BeerId: Int): Call<Beer>

        @POST("beers")
        fun saveBeer(@Body Beer: Beer): Call<Beer>

        @DELETE("beers/{id}")
        fun deleteBeer(@Path("id") id: Int): Call<Beer>

        @PUT("beers/{id}")
        fun updateBeers(@Path("id") id: Int, @Body Beer: Beer): Call<Beer>
        @GET("beers/{user}")
        fun getBeerByUser(@Path("user") user: String): Call<List<Beer>>
}