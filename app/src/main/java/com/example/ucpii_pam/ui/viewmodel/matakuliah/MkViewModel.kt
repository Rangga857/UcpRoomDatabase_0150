package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.data.entity.MataKuliah
import com.example.ucpii_pam.repository.RepositoryDsn
import com.example.ucpii_pam.repository.RepositoryMK

class MataKuliahViewModel(
    private val repositoryMk: RepositoryMK,
    private val repositoryDsn: RepositoryDsn): ViewModel(){

}

data class MKUiState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null,
    val dosenOptions: List<Dosen> = emptyList()
)

data class FormErrorState(
    val kodeMk: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosen_pengampu: String? = null,
) {
    fun isFormValid(): Boolean {
        return kodeMk == null && nama == null
                && sks == null && semester == null
                && jenis == null && dosen_pengampu == null
    }
}

fun MataKuliahEvent.toMataKuliahEntity():MataKuliah = MataKuliah(
    kodeMk = kodeMk,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosen_pengampu = dosen_pengampu
)


data class MataKuliahEvent(
    val kodeMk: String ="",
    val nama: String = "",
    val sks: String= "",
    val semester: String = "",
    val jenis: String= "",
    val dosen_pengampu: String = "",
)