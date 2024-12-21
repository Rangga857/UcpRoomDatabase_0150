package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpii_pam.ui.viewmodel.matakuliah.PenyediaMkViewModel
import com.example.ucpii_pam.ui.viewmodel.matakuliah.UpdateViewModel

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaMkViewModel.Factory)

){

}