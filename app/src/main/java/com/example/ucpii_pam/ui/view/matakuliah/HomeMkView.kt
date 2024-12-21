package com.example.ucpii_pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ucpii_pam.data.entity.MataKuliah
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ucpii_pam.ui.viewmodel.matakuliah.HomeMkUiState

@Composable
fun BodyHomeMkView(
    homeMkUiState: HomeMkUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


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