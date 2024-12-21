package com.example.ucpii_pam.ui.viewmodel.matakuliah

import com.example.ucpii_pam.data.entity.MataKuliah

data class HomeMkUiState(
    val listMK: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)