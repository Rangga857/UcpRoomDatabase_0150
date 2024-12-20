package com.example.ucpii_pam.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucpii_pam.MkDsApp

object PenyediaDsnViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeDsnViewModel(
                MkDsApp().containerApp.repositoryDsn)
        }
        initializer {
            DosenViewModel(
                MkDsApp().containerApp.repositoryDsn)
        }
    }
}

fun  CreationExtras.MkDsApp(): MkDsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MkDsApp)