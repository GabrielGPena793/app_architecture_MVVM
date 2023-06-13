package com.example.guests.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.guests.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM guest WHERE id = :id")
    fun getById(id: Int): GuestModel

    @Query("SELECT * FROM guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>

}