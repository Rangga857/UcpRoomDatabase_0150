package com.example.ucpii_pam.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.repository.RepositoryDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoryDsn.getAllDosen()
        .filterNotNull()
        .map {
            HomeUiState(
                dosenList = it.toList(),
                isloading = false,
            )
        }
        .onStart {
            emit(HomeUiState(isloading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isloading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isloading = true
            )
        )

}

data class HomeUiState(
    val dosenList: List<Dosen> = listOf(),
    val isloading : Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String =""
)