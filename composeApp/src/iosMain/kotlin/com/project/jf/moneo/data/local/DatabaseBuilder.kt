package com.project.jf.moneo.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoneoDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<MoneoDatabase>(
        name = dbFilePath,
    ).setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
}