package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucpii_pam.data.entity.MataKuliah
import com.example.ucpii_pam.ui.viewmodel.matakuliah.DetailMkUiState
import com.example.ucpii_pam.ui.viewmodel.matakuliah.toMataKuliahEntity


@Composable
fun BodyDetailMk(
    modifier: Modifier = Modifier,
    detailMkUiState: DetailMkUiState = DetailMkUiState(),
    onDeleteClick: () ->Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false)  }
    when{
        detailMkUiState.isLoading -> {
            Box(
                modifier=modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        detailMkUiState.isUiMkEventNotEmpty ->{
            Column(
                modifier=modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMk(
                    matakuliah = detailMkUiState.detailUiEvent.toMataKuliahEntity(),
                    modifier = modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired){
                    DeleteConfirmationDialog(
                        onDeleteConfirm =
                        {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailMkUiState.isUiMkEventEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun ItemDetailMk(
    modifier: Modifier = Modifier,
    matakuliah: MataKuliah
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMk(judul = "Kode Mata Kuliah", isinya = matakuliah.kodeMk)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMk(judul = "Nama", isinya = matakuliah.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMk(judul = "Jumlah Sks", isinya = matakuliah.sks)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMk(judul = "Semester", isinya = matakuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMk(judul = "Jenis Mata Kuliah", isinya = matakuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMk(judul = "Dosen Pengampu Mata Kuliah", isinya = matakuliah.dosen_pengampu)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }

}

@Composable
fun ComponentDetailMk(
    modifier: Modifier = Modifier,
    judul : String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Text(
            text = "$isinya ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest= { },
        title = {
            Text("Delete Data")
        },
        text = {
            Text("Apakah anda yakin ingin menghapus data?")
        },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel)
            {
                Text(text = "Cancel")
            }
        },
        confirmButton =
        {
            TextButton(onClick = onDeleteConfirm)
            {
                Text(text = "Yes")
            }
        }
    )
}