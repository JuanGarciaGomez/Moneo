package com.project.jf.moneo.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.jf.moneo.data.local.dao.AccountDao
import com.project.jf.moneo.data.local.dao.CategoryDao
import com.project.jf.moneo.data.local.dao.TransactionDao
import com.project.jf.moneo.data.local.entity.Account
import com.project.jf.moneo.data.local.entity.Category
import com.project.jf.moneo.data.local.entity.Transaction

@Database(
    entities = [Account::class, Category::class, Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}