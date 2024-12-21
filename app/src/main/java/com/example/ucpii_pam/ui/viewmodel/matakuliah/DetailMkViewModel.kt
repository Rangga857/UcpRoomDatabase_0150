package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpii_pam.data.entity.MataKuliah
import com.example.ucpii_pam.repository.RepositoryMK
import com.example.ucpii_pam.ui.navigation.DestinasiDetailMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DetailMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK

): ViewModel(){
    private val _kodeMk: String = checkNotNull(savedStateHandle[DestinasiDetailMk.kodeMk])

    val detailUiState: StateFlow<DetailMkUiState> = repositoryMK.getByIdmatakuliah(_kodeMk)
        .filterNotNull()
        .map {
            DetailMkUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMkUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailMkUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue =
            DetailMkUiState(
                isLoading = true)
        )
}

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