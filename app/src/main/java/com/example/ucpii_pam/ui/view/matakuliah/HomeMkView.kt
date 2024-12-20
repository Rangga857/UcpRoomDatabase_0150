package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ucpii_pam.data.entity.MataKuliah
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpii_pam.ui.costumwidget.TopAppBar
import com.example.ucpii_pam.ui.viewmodel.matakuliah.HomeMkUiState
import com.example.ucpii_pam.ui.viewmodel.matakuliah.HomeMkViewModel
import com.example.ucpii_pam.ui.viewmodel.matakuliah.PenyediaMkViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMkView(
    viewModel: HomeMkViewModel = viewModel(factory = PenyediaMkViewModel.Factory),
    onAddMk : () -> Unit ={ },
    onDetailClick: (String) -> Unit = { },
    onBack:() -> Unit = { },
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 21.dp),
        topBar = {
            TopAppBar(
                judul = "Daftar MataKuliah",
                showBackButton = false,
                onBack = { },
                modifier = Modifier
            )
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                    FloatingActionButton(
                        onClick = onAddMk,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambah Matakuliah"
                        )
                    }
                FloatingActionButton(
                    onClick = onBack,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Kembali"
                    )
                }
            }
        }

    ) { innerPadding ->
        val homeMkUiState by viewModel.homeUIState.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            BodyHomeMkView(
                homeMkUiState = homeMkUiState,
                onClick = {
                    onDetailClick(it)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BodyHomeMkView(
    homeMkUiState: HomeMkUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when{
        homeMkUiState.isLoading -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeMkUiState.isError ->{
            LaunchedEffect(homeMkUiState.errorMessage) {
                homeMkUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeMkUiState.listMK.isEmpty() ->{
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data mata kuliah.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else ->{
            ListMataKuliah(
                listMk = homeMkUiState.listMK,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMataKuliah(
    listMk: List<MataKuliah>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listMk,
            itemContent = {mk ->
                CardMk(
                    mk = mk,
                    onClick = {onClick(mk.kodeMk)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMk(
    mk : MataKuliah,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
){
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person,
                    contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mk.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange,
                    contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mk.semester,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = mk.dosen_pengampu,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}