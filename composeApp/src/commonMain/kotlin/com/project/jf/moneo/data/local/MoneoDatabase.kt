package com.project.jf.moneo.data.local

import ControlPeriodEntity
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.project.jf.moneo.data.local.dao.ControlPeriodDao
import com.project.jf.moneo.data.local.dao.TransactionDao
import com.project.jf.moneo.data.local.entity.TransactionEntity

const val DATABASE_NAME = "moneo.db"

@Database(
    entities = [ControlPeriodEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(MoneoDatabaseConstructor::class)
abstract class MoneoDatabase : RoomDatabase() {
    abstract fun controlPeriodDao(): ControlPeriodDao
    abstract fun transactionDao(): TransactionDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object MoneoDatabaseConstructor : RoomDatabaseConstructor<MoneoDatabase> {
    override fun initialize(): MoneoDatabase
}