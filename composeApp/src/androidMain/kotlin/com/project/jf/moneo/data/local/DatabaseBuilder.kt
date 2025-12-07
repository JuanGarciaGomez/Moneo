package com.project.jf.moneo.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual object DatabaseBuilder {
    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    actual fun build(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = appContext.getDatabasePath("moneo.db")
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}