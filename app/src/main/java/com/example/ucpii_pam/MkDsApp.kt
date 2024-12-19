package com.example.ucpii_pam

import android.app.Application
import com.example.ucpii_pam.dependeciesinjection.ContainerApp

class MkDsApp : Application() {
    lateinit var containerApp: ContainerApp
    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}