package com.example.guests.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuessFormRepository

class GuessFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuessFormRepository.getInstance(application)

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }
}