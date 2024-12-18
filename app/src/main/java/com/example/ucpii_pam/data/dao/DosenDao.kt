package com.example.ucpii_pam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucpii_pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Query("SELECT * FROM dosen")
    fun getAllDosen(): Flow<List<Dosen>>

    @Insert
    suspend fun  insertDosen(dosen: Dosen)
}