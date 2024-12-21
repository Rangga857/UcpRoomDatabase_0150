package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.data.entity.MataKuliah
import com.example.ucpii_pam.repository.RepositoryDsn
import com.example.ucpii_pam.repository.RepositoryMK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MataKuliahViewModel(
    private val repositoryMk: RepositoryMK,
    private val repositoryDsn: RepositoryDsn): ViewModel(){

    var mkUiState by mutableStateOf(MKUiState())
    val listDosen = mutableStateListOf<Dosen>()

    init {
        viewModelScope.launch {
            repositoryDsn.getAllDosen().collectLatest { dosenList ->
                listDosen.clear()
                listDosen.addAll(dosenList)
                mkUiState = mkUiState.copy(dosenOptions = dosenList)
            }
        }
    }

    fun updateState(mataKuliahEvent: MataKuliahEvent){
        mkUiState = mkUiState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }


    fun validateFields():Boolean{
        val event = mkUiState.mataKuliahEvent
        val errorState = FormErrorState(
            kodeMk = if (event.kodeMk.isEmpty()) "Kode MK tidak boleh kosong" else null,
            nama = if (event.nama.isEmpty()) "Nama MataKuliah tidak boleh kosong" else null,
            sks = if (event.sks.isEmpty()) "SKS tidak boleh kosong" else null,
            semester = if (event.semester.isEmpty()) "Semester tidak boleh kosong" else null,
            jenis = if (event.jenis.isEmpty()) "Jenis MataKuliah tidak boleh kosong" else null,
            dosen_pengampu = if (event.dosen_pengampu.isEmpty()) "Dosen Pengampu tidak boleh kosong" else null
        )
        mkUiState = mkUiState.copy(isEntryValid = errorState)
        return errorState.isFormValid()
    }

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