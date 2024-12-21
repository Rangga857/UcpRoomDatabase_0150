package com.example.ucpii_pam.ui.viewmodel.matakuliah

import com.example.ucpii_pam.data.entity.MataKuliah

data class DetailMkUiState(
    val detailUiEvent: MataKuliahEvent = MataKuliahEvent(),
    val isLoading :  Boolean = false,
    val isError : Boolean = false,
    val errorMessage : String = ""
){
    val isUiMkEventEmpty : Boolean
        get() = detailUiEvent == MataKuliahEvent()
    val isUiMkEventNotEmpty : Boolean
        get() = detailUiEvent != MataKuliahEvent()
}


fun MataKuliah.toDetailUiEvent(): MataKuliahEvent{
    return MataKuliahEvent(
        kodeMk = kodeMk,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosen_pengampu = dosen_pengampu
    )
}