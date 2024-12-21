package com.example.ucpii_pam.ui.view.dosen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpii_pam.ui.costumwidget.TopAppBar
import com.example.ucpii_pam.ui.navigation.AlamatNavigasi
import com.example.ucpii_pam.ui.viewmodel.dosen.DosenEvent
import com.example.ucpii_pam.ui.viewmodel.dosen.DosenUiState
import com.example.ucpii_pam.ui.viewmodel.dosen.DosenViewModel
import com.example.ucpii_pam.ui.viewmodel.dosen.FormErrorState
import com.example.ucpii_pam.ui.viewmodel.dosen.PenyediaDsnViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object DestinasiInsertDsn : AlamatNavigasi {
    override val route: String = "insertdsn"
}

@Composable
fun InsertDsnView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaDsnViewModel.Factory)
 ){
    val dsnuiState = viewModel.dosenuiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(dsnuiState.snackbarMessage) {
        dsnuiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Tambah Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) { padding->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyDsn(
                dsnuiState = dsnuiState,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()){
                            viewModel.saveData()
                            withContext(Dispatchers.Main){
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyDsn(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit = {},
    dsnuiState: DosenUiState,
    onClick: ()-> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        FormDosen(
            dosenEvent = dsnuiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = dsnuiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
    }
    Spacer(modifier = modifier.padding(10.dp))
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Simpan")
    }

}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val JenisKelamin = listOf("laki-Laki", "Perempuan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "NIDN")
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
                            },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan NIDN") }
        )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )
        Text(text = "Nama Dosen")
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Dosen") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = "Jenis Kelamin",
            fontSize = 16.sp)
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            JenisKelamin.forEach {jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = dosenEvent.jeniskelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jeniskelamin = jk))
                        },
                    )

                    Text(
                        text = jk)
                }
            }
        }
        Text(
            text = errorState.jeniskelamin ?: "",
            color = Color.Red
        )
    }
}

