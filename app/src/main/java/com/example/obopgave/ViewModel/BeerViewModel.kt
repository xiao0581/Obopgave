package com.example.obopgave.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.obopgave.Repository.BeerRepository
import androidx.compose.runtime.State
import retrofit2.http.GET

class BeerViewModel:ViewModel() {
    private val repository = BeerRepository()
    val BeersFlow: State<List<Beer>> = repository.BeersFlow
    val errorMessageFlow: State<String> = repository.errorMessageFlow

    // TODO use reloadingFlow to show loading indicator
    val reloadingFlow: State<Boolean> = repository.isLoadingBeers

    init {
        reload()
    }

    fun reload() {
        try {
            repository.getBeers()
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to reload beers: ${e.message}")
        }
    }
    fun GetBeerByUser(user: String) {
        try {
            repository.getBeerByUser(user)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to get beer by user: ${e.message}")
        }
    }

    fun add(Beer: Beer) {
        try {
            repository.add(Beer)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to add beer: ${e.message}")
        }
    }

    fun update(BeerId: Int, Beer: Beer) {
        try {
            repository.update(BeerId, Beer)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to update beer: ${e.message}")
        }
    }

    fun remove(Beer: Beer) {
        try {
            repository.delete(Beer.id)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to remove beer: ${e.message}")
        }
    }

    fun sortBooksByName(ascending: Boolean) {
        try {
            repository.sortBooksByName(ascending)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to sort beers by name: ${e.message}")
        }
    }

    fun sortBooksByAbv(ascending: Boolean) {
        try {
            repository.sortBooksByAbv(ascending)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to sort beers by ABV: ${e.message}")
        }
    }

    fun filterByName(titleFragment: String) {
        try {
            repository.filterByTName(titleFragment)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to filter beers by name: ${e.message}")
        }
    }

    fun filterByAbv(minAbv: Double) {
        try {
            repository.filterByAbv(minAbv)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to filter beers by ABV: ${e.message}")
        }
    }

    fun filterByNameAndAbv(nameFragment: String, maxAbv: Double) {
        try {
            repository.filterByNameAndAbv(nameFragment, maxAbv)
        } catch (e: Exception) {
            Log.e("BeerViewModel", "Failed to filter beers by name and ABV: ${e.message}")
        }
    }
}