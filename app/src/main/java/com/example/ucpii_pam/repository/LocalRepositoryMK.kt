package com.example.ucpii_pam.repository

import com.example.ucpii_pam.data.dao.MataKuliahDao
import com.example.ucpii_pam.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMK(
    private val matakuliahDao: MataKuliahDao
) : RepositoryMK {

    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return matakuliahDao.getAllmatakuliah()
    }

    override fun getByIdmatakuliah(kodeMk: String): Flow<MataKuliah> {
        return matakuliahDao.getByIdmatakuliah(kodeMk)
    }

    override suspend fun insertmatakuliah(matakuliah: MataKuliah) {
        matakuliahDao.insertmatakuliah(matakuliah)
    }

    override suspend fun deletematakuliah(matakuliah: MataKuliah) {
        matakuliahDao.deletematakuliah(matakuliah)
    }

    override suspend fun updatematakuliah(matakuliah: MataKuliah) {
        matakuliahDao.updatematakuliah(matakuliah)
    }
}