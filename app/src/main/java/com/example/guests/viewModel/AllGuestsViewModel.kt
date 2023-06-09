package com.example.guests.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuests

    fun getAll() {
      listGuests.value =  repository.getAll()
    }
}