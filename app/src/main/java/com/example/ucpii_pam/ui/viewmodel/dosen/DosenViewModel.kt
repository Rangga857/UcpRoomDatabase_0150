package com.example.ucpii_pam.ui.viewmodel.dosen

import com.example.ucpii_pam.data.entity.Dosen


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