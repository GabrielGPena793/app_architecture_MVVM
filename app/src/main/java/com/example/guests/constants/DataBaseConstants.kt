package com.example.guests.constants

class DataBaseConstants private constructor() {

    object GUEST {
        const val TABLE_NAME = "guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}