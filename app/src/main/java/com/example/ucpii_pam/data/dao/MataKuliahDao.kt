package com.example.ucpii_pam.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucpii_pam.data.entity.MataKuliah

interface MataKuliahDao {
    @Query("SELECT * FROM matakuliah")
    fun getAllmatakuliah(): List<MataKuliah>

    @Query("SELECT * FROM matakuliah WHERE kodeMk = :kodeMk")
    fun getByIdmatakuliah(kodeMk: String): MataKuliah

    @Insert
    suspend fun insertmatakuliah(matakuliah: MataKuliah)

    @Delete
    suspend fun deletematakuliah(matakuliah: MataKuliah)

    @Update
    suspend fun updatematakuliah(matakuliah: MataKuliah)
}