package com.example.obopgave.Repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.obopgave.ViewModel.Beer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class BeerRepository {

    private val baseUrl = "https://anbo-restbeer.azurewebsites.net/api/"
    private val beerService: BeerService
    val BeersFlow: MutableState<List<Beer>> = mutableStateOf(listOf())
    val isLoadingBeers = mutableStateOf(false)
    val errorMessageFlow = mutableStateOf("")


    private var originalBeers: List<Beer> = listOf()

    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        beerService = build.create(BeerService::class.java)
        getBeers()
    }


    fun getBeers() {
        isLoadingBeers.value = true
        beerService.getAllBeers().enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                isLoadingBeers.value = false
                if (response.isSuccessful) {
                    val beerList: List<Beer>? = response.body()
                    originalBeers = beerList ?: emptyList()
                    BeersFlow.value = originalBeers
                    errorMessageFlow.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message

                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                isLoadingBeers.value = false
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message

            }
        })
    }

    fun add(Beer: Beer) {
        beerService.saveBeer(Beer).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {

                    getBeers()  // 添加后重新获取所有数据
                    errorMessageFlow.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message

                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message

            }
        })
    }

    fun delete(id: Int) {
        Log.d("APPLE", "Delete: $id")
        beerService.deleteBeer(id).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {

                    errorMessageFlow.value = ""
                    getBeers()  // 删除后重新获取所有数据
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message

                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message
                Log.d("APPLE", "Not deleted $message")
            }
        })
    }

    fun update(BeerId: Int, Beer: Beer) {
        Log.d("APPLE", "Update: $BeerId $Beer")
        beerService.updateBeers(BeerId, Beer).enqueue(object : Callback<Beer> {
            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {
                if (response.isSuccessful) {
                    errorMessageFlow.value = ""
                    getBeers()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message

                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message

            }
        })
    }

    fun getBeerByUser(user: String) {
        isLoadingBeers.value = true
        beerService.getBeerByUser(user).enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                isLoadingBeers.value = false
                if (response.isSuccessful) {
                    val beerList: List<Beer>? = response.body()
                    originalBeers = beerList ?: emptyList()
                    BeersFlow.value = originalBeers
                    errorMessageFlow.value = ""
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageFlow.value = message

                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                isLoadingBeers.value = false
                val message = t.message ?: "No connection to back-end"
                errorMessageFlow.value = message

            }
        })
    }

    // 使用原始数据进行排序
    fun sortBooksByName(ascending: Boolean) {

        BeersFlow.value = if (ascending) {
            originalBeers.sortedBy { it.name }
        } else {
            originalBeers.sortedByDescending { it.name }
        }
    }

    fun sortBooksByAbv(ascending: Boolean) {

        BeersFlow.value = if (ascending) {
            originalBeers.sortedBy { it.abv }
        } else {
            originalBeers.sortedByDescending { it.abv }
        }
    }


    fun filterByTName(nameFragment: String) {
        if (nameFragment.isEmpty()) {
            BeersFlow.value = originalBeers
        } else {
            BeersFlow.value = originalBeers.filter {
                it.name.contains(nameFragment, ignoreCase = true)
            }
        }
    }


    fun filterByAbv(minAbv: Double) {
        BeersFlow.value = originalBeers.filter {
            it.abv <= minAbv
        }

    }

    fun filterByNameAndAbv(nameFragment: String, maxAbv: Double) {
        BeersFlow.value = originalBeers.filter {
            it.name.contains(nameFragment, ignoreCase = true) && it.abv <= maxAbv
        }
    }
}