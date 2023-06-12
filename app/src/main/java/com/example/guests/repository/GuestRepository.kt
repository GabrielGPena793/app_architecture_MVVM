package com.example.guests.repository

import android.content.ContentValues
import android.content.Context
import com.example.guests.constants.DataBaseConstants
import com.example.guests.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guessDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
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

    fun update(guest: GuestModel): Boolean {
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

    fun getById(id: Int): GuestModel? {
        var guest: GuestModel? = null
        val columName = DataBaseConstants.GUEST.COLUMNS.NAME
        val columPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

        try {
            val db = guessDataBase.readableDatabase

            val where = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            val cursor =
                db.query(
                    DataBaseConstants.GUEST.TABLE_NAME, null,
                    where, args, null, null, null
                )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(columName))
                    val presence = cursor.getInt(cursor.getColumnIndexOrThrow(columPresence))

                    guest = GuestModel(id, name, presence == 1)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return guest
        }

        return guest
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guessDataBase.writableDatabase

            val where = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, where, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val columId = DataBaseConstants.GUEST.COLUMNS.ID
        val columName = DataBaseConstants.GUEST.COLUMNS.NAME
        val columPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

        try {
            val db = guessDataBase.readableDatabase

            val select = arrayOf(columId, columName, columPresence)

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, select,
                null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(columId))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(columName))
                    val presence = cursor.getInt(cursor.getColumnIndexOrThrow(columPresence))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }

        return list
    }

    fun getPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val columId = DataBaseConstants.GUEST.COLUMNS.ID
        val columName = DataBaseConstants.GUEST.COLUMNS.NAME
        val columPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

        try {
            val db = guessDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(columId))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(columName))
                    val presence = cursor.getInt(cursor.getColumnIndexOrThrow(columPresence))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }

        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        val columId = DataBaseConstants.GUEST.COLUMNS.ID
        val columName = DataBaseConstants.GUEST.COLUMNS.NAME
        val columPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

        try {
            val db = guessDataBase.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(columId))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(columName))
                    val presence = cursor.getInt(cursor.getColumnIndexOrThrow(columPresence))

                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }

        return list
    }
}