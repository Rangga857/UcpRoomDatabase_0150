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

    fun updatemkState(mataKuliahEvent: MataKuliahEvent){
        updatemkUIState = updatemkUIState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    fun validateFields() : Boolean{
        val event = updatemkUIState.mataKuliahEvent
        val errorState = FormErrorState(
            kodeMk = if (event.kodeMk.isNotEmpty()) null else "Kode MK tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosen_pengampu = if (event.dosen_pengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )

        updatemkUIState = updatemkUIState.copy(isEntryValid = errorState)
        return errorState.isFormValid()
    }

    fun updateData(){
        val currentEvent = updatemkUIState.mataKuliahEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMK.updatematakuliah(currentEvent.toMataKuliahEntity())
                    updatemkUIState = updatemkUIState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackbarMessage diatur: ${updatemkUIState.snackbarMessage}")
                }
                catch (e: Exception){
                    updatemkUIState = updatemkUIState.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        }
        else{
            updatemkUIState = updatemkUIState.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage(){
        updatemkUIState = updatemkUIState.copy(
            snackbarMessage = null
        )
    }

}
