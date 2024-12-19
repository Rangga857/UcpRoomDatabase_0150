package com.example.ucpii_pam.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.repository.RepositoryDsn
import kotlinx.coroutines.launch

class DosenViewModel (private val repositoryDsn: RepositoryDsn): ViewModel(){
    var dosenuiState by mutableStateOf(DosenUiState())

    fun updateState(dosenEvent: DosenEvent){
        dosenuiState = dosenuiState.copy(
            dosenEvent = dosenEvent
        )
    }

    fun validateFields(): Boolean {
        val event = dosenuiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isEmpty()) "NIDN tidak boleh kosong" else null,
            nama = if (event.nama.isEmpty()) "Nama tidak boleh kosong" else null,
            jeniskelamin = if (event.jeniskelamin.isEmpty()) "Jenis Kelamin tidak boleh kosong" else null
        )
        dosenuiState = dosenuiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val  currentEvent = dosenuiState.dosenEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryDsn.insertDosen(currentEvent.toDosenEntity())
                    dosenuiState = dosenuiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception){
                    dosenuiState =dosenuiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }
        else{
            dosenuiState = dosenuiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data yang anda masukkan"
            )
        }
    }
    fun resetSnackBarMessage(){
        dosenuiState=dosenuiState.copy(snackbarMessage = null)
    }
}

data class DosenUiState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val nidn : String ?=null,
    val nama : String ?=null,
    val jeniskelamin : String ?=null,
){
    fun isValid(): Boolean {
        return nidn == null
                && nama == null
                && jeniskelamin == null
    }
}

fun DosenEvent.toDosenEntity():Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jeniskelamin = jeniskelamin
)

data class DosenEvent(
    val nidn : String ="",
    val nama : String ="",
    val jeniskelamin : String ="",
)