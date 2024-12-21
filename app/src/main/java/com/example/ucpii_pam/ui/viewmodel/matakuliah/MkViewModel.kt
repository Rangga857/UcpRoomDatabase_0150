package com.example.ucpii_pam.ui.viewmodel.matakuliah

import com.example.ucpii_pam.data.entity.MataKuliah

fun MataKuliahEvent.toMataKuliahEntity():MataKuliah = MataKuliah(
    kodeMk = kodeMk,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosen_pengampu = dosen_pengampu
)


data class MataKuliahEvent(
    val kodeMk: String ="",
    val nama: String = "",
    val sks: String= "",
    val semester: String = "",
    val jenis: String= "",
    val dosen_pengampu: String = "",
)