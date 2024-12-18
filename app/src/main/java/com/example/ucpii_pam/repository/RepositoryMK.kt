package com.example.ucpii_pam.repository

import com.example.ucpii_pam.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow


interface RepositoryMK {
    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    fun getByIdmatakuliah(kodeMk: String): Flow<MataKuliah>

    suspend fun insertmatakuliah(matakuliah: MataKuliah)

    suspend fun deletematakuliah(matakuliah: MataKuliah)

    suspend fun updatematakuliah(matakuliah: MataKuliah)

}