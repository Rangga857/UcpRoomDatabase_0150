package com.example.ucpii_pam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class MataKuliah (
    @PrimaryKey
    val kodeMk: String,
    val nama: String,
    val sks: String,
    val semester: String,
    val jenis: String,
    val dosen_pengampu: String,
)