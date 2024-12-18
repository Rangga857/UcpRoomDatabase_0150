package com.example.ucpii_pam.repository

import com.example.ucpii_pam.data.dao.DosenDao
import com.example.ucpii_pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDsn {
    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }
}