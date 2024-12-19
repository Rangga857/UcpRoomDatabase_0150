package com.example.ucpii_pam.dependeciesinjection

import android.content.Context
import com.example.ucpii_pam.data.database.MkDsDatabase
import com.example.ucpii_pam.repository.LocalRepositoryDsn
import com.example.ucpii_pam.repository.LocalRepositoryMK
import com.example.ucpii_pam.repository.RepositoryDsn
import com.example.ucpii_pam.repository.RepositoryMK

interface InterfaceApp{
    val repositoryMk: RepositoryMK
    val repositoryDsn: RepositoryDsn
}

class ContainerApp(private val context: Context) : InterfaceApp{
    override val repositoryDsn : RepositoryDsn by lazy {
        LocalRepositoryDsn(MkDsDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMk : RepositoryMK by lazy {
        LocalRepositoryMK(MkDsDatabase.getDatabase(context).matakuliahDao())
    }
}