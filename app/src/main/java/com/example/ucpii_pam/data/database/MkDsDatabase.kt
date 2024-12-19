package com.example.ucpii_pam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucpii_pam.data.dao.DosenDao
import com.example.ucpii_pam.data.dao.MataKuliahDao
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.data.entity.MataKuliah

@Database(entities = [MataKuliah::class, Dosen::class],version = 1, exportSchema =  false)
abstract class MkDsDatabase : RoomDatabase() {

    abstract fun matakuliahDao(): MataKuliahDao
    abstract fun dosenDao(): DosenDao

    companion object {
        @Volatile
        private var INSTANCE: MkDsDatabase? = null

        fun getDatabase(context: Context): MkDsDatabase {
            return (INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MkDsDatabase::class.java,
                    "MkDsDatabase"
                )
                    .build().also { INSTANCE = it }
            })
        }
    }
}