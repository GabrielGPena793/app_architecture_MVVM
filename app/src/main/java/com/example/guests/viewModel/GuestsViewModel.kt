package com.example.guests.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuests

    fun getAll() {
      listGuests.value =  repository.getAll()
    }

    fun getPresent() {
        listGuests.value = repository.getPresent()
    }

    fun getAbsent() {
        listGuests.value = repository.getAbsent()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}