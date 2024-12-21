package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpii_pam.repository.RepositoryDsn
import com.example.ucpii_pam.repository.RepositoryMK
import com.example.ucpii_pam.ui.navigation.DestinasiUpdateMk
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK,
    private val repositoryDsn: RepositoryDsn
) : ViewModel(){
    var updatemkUIState by mutableStateOf(MKUiState())
        private set
    private val _kodeMk: String = checkNotNull(savedStateHandle[DestinasiUpdateMk.kodeMk])
    init {
        viewModelScope.launch {
            val mataKuliah = repositoryMK.getByIdmatakuliah(_kodeMk)
                .filterNotNull()
                .first()

            val dosenList = repositoryDsn.getAllDosen().first()

            updatemkUIState = MKUiState(
                mataKuliahEvent = mataKuliah.toDetailUiEvent(),
                dosenOptions = dosenList
            )
        }
    }

}
