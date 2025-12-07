package com.project.jf.moneo.data.local

import androidx.room.RoomDatabase

// Declaración multiplataforma
expect object DatabaseBuilder {
    fun build(): RoomDatabase.Builder<AppDatabase>
}

// Función helper para obtener la instancia
fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return DatabaseBuilder.build()
}