package com.project.jf.moneo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val icon: String, // Nombre del ícono o emoji
    val color: String, // Color en formato hexadecimal
    val type: String // "income" o "expense"
)