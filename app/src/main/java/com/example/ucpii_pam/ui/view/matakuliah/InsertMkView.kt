package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucpii_pam.data.entity.Dosen
import com.example.ucpii_pam.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucpii_pam.ui.viewmodel.matakuliah.MKUiState
import com.example.ucpii_pam.ui.viewmodel.matakuliah.MataKuliahEvent
import com.example.ucpii_pam.ui.widget.DynamicSelectedTextField

@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    matkuluiState: MKUiState,
    dosenOptions: List<Dosen>,
    onClick: ()-> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                FormMataKuliah(
                    mataKuliahEvent = matkuluiState.mataKuliahEvent,
                    onValueChange = onValueChange,
                    errorState = matkuluiState.isEntryValid,
                    dosenOptions = dosenOptions,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text("Simpan")
            }
        }
    }
}


@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    dosenOptions: List<Dosen> = emptyList(),
    modifier: Modifier = Modifier
) {
    val semesterList = listOf("Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8")
    val sksList = listOf("1", "2", "3")
    val jenis = listOf("Teori", "Praktikum")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.kodeMk,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kodeMk = it))
            },
            isError = errorState.kodeMk != null,
            placeholder = { Text("Masukkan Kode MK") }
        )
        Text(
            text = errorState.kodeMk ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.nama,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(nama = it))
            },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Mata Kuliah") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        Spacer(modifier = modifier.padding(4.dp))
        Text(
            text = "Jumlah SKS",
            fontSize = 16.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            sksList.forEach { sks ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mataKuliahEvent.sks == sks,
                        onClick = {
                            onValueChange(mataKuliahEvent.copy(sks = sks))
                        }
                    )
                    Text(text = sks)
                }
            }
        }
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )
        Text(text = "Semester")
        DynamicSelectedTextField(
            selectedValue = mataKuliahEvent.semester,
            options = semesterList,
            label = "Semester",
            onValueChangedEvent = { onValueChange(mataKuliahEvent.copy(semester = it)) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Jenis Mata Kuliah")
        DynamicSelectedTextField(
            selectedValue = mataKuliahEvent.jenis,
            options = jenis,
            label = "Jenis Mata Kuliah",
            onValueChangedEvent = { onValueChange(mataKuliahEvent.copy(jenis = it)) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Dosen Pengampu Mata Kuliah")
        DynamicSelectedTextField(
            selectedValue = mataKuliahEvent.dosen_pengampu,
            options = dosenOptions.map { it.nama },
            label = "Dosen Pengampu",
            onValueChangedEvent = { dosenPengampu ->
                onValueChange(mataKuliahEvent.copy(dosen_pengampu = dosenPengampu))
            },
            modifier = Modifier.fillMaxWidth()
        )
        println("Dosen Options: ${dosenOptions.map { it.nama }}")

        Text(
            text = errorState.dosen_pengampu ?: "",
            color = Color.Red
        )
    }
}
