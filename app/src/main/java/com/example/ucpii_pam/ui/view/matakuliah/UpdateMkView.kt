package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
    val uimkState = viewModel.updatemkUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

}