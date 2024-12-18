package com.example.ucpii_pam.repository

import com.example.ucpii_pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {
    fun getAllDosen(): Flow<List<Dosen>>

    suspend fun insertDosen(dosen: Dosen)
}