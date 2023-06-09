package com.example.guests.repository

import android.content.ContentValues
import android.content.Context
import com.example.guests.constants.DataBaseConstants
import com.example.guests.model.GuestModel

class GuessFormRepository private constructor(context: Context) {

    private val guessDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuessFormRepository

        fun getInstance(context: Context): GuessFormRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuessFormRepository(context)
            }

            return repository
        }
    }

    fun insert(guest: GuestModel) : Boolean {
        return try {
            val db = guessDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel) : Boolean {
       return try {
           val db = guessDataBase.writableDatabase

           val presence = if (guest.presence) 1 else 0

           val values = ContentValues()
           values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
           values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

           val where = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
           val args = arrayOf(guest.id.toString())

           db.update(DataBaseConstants.GUEST.TABLE_NAME, values, where, args)
           true
       } catch (e: Exception) {
           false
       }
    }
}