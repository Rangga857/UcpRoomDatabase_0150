package com.example.ucpii_pam.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucpii_pam.MkDsApp

object PenyediaMkViewModel{
    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                MkDsApp().containerApp.repositoryMk,
                MkDsApp().containerApp.repositoryDsn
            )
        }

        initializer {
            HomeMkViewModel(
                MkDsApp().containerApp.repositoryMk
            )
        }
        initializer {
            DetailMkViewModel(
                this.createSavedStateHandle(),
                MkDsApp().containerApp.repositoryMk
            )
        }
        initializer {
            UpdateViewModel(
                this.createSavedStateHandle(),
                MkDsApp().containerApp.repositoryMk,
                MkDsApp().containerApp.repositoryDsn
            )
        }
    }
}

fun CreationExtras.MkDsApp():MkDsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MkDsApp)