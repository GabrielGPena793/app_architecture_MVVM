package com.example.guests.repository

import android.content.Context
import com.example.guests.model.GuestModel

class GuestRepository (context: Context) {

    private val guessDataBase = GuestDataBase.getInstance(context).guestDAO()

    fun insert(guest: GuestModel): Boolean {
        return guessDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guessDataBase.update(guest) > 0
    }

    fun getById(id: Int): GuestModel {
       return  guessDataBase.getById(id)
    }

    fun delete(id: Int) {
        val guest = getById(id)
        guessDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel> {
        return guessDataBase.getAll()
    }

    fun getPresent(): List<GuestModel> {
       return guessDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
      return guessDataBase.getAbsent()
    }
}