package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucpii_pam.ui.viewmodel.matakuliah.MataKuliahEvent

@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    dosenOptions: List<Dosen> = emptyList(),
    modifier: Modifier = Modifier
) {

}
