package com.example.guests.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.model.GuestModel
import com.example.guests.model.SuccessFailure
import com.example.guests.repository.GuestRepository

class GuessFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel?>()
    val _guest: LiveData<GuestModel?> = guestModel

    fun save(guest: GuestModel): SuccessFailure {
        var success: Boolean = false
        var message: String = ""

        if (guest.id == 0) {
            success = repository.insert(guest)

            if (success) {
                message = "Inserção feita com sucesso"
            }

        } else {
            success = repository.update(guest)

            if (success) {
                message = "Update feito com sucesso"
            }
        }

        if (message == "") {
            message = "Falha, tente novamente!"
        }

        return SuccessFailure(success, message)
    }

    fun getById(id: Int) {
       val guest = repository.getById(id)
        guestModel.value = guest
    }
}