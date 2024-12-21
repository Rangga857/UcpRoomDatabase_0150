package com.example.ucpii_pam.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucpii_pam.ui.viewmodel.dosen.DosenEvent
import com.example.ucpii_pam.ui.viewmodel.dosen.FormErrorState

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

