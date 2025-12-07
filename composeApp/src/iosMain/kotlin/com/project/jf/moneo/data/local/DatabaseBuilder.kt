package com.project.jf.moneo.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

actual object DatabaseBuilder {
    actual fun build(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/moneo.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath
        )
    }
}