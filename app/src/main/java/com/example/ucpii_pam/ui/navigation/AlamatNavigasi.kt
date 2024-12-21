package com.example.ucpii_pam.ui.navigation

interface AlamatNavigasi{
    val route : String
}

object DestinasiHalamanUtama : AlamatNavigasi{
    override val route: String = "utama"
}

object DestinasiHomeDsn : AlamatNavigasi{
    override val route: String = "homedsn"
}

object DestinasiHomeMk : AlamatNavigasi{
    override val route: String = "homemk"

}

object DestinasiDetailMk : AlamatNavigasi{
    override val route = "detaillmk"
    const val kodeMk = "kodeMk"
    val routeWithArgs = "$route/{$kodeMk}"
}

object DestinasiUpdateMk : AlamatNavigasi{
    override val route = "updatemk"
    const val kodeMk = "kodemk"
    val routeWithArgs = "$route/{$kodeMk}"
}
