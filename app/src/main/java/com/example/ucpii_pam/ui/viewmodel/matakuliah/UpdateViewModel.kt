package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucpii_pam.repository.RepositoryDsn
import com.example.ucpii_pam.repository.RepositoryMK

class UpdateViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK,
    private val repositoryDsn: RepositoryDsn
) : ViewModel(){

}
