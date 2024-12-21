package com.example.ucpii_pam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucpii_pam.ui.view.HalamanUtamaView
import com.example.ucpii_pam.ui.view.dosen.DestinasiInsertDsn
import com.example.ucpii_pam.ui.view.dosen.HomeDsnView
import com.example.ucpii_pam.ui.view.dosen.InsertDsnView
import com.example.ucpii_pam.ui.view.matakuliah.DestinasiInsertMk
import com.example.ucpii_pam.ui.view.matakuliah.DetailMkView
import com.example.ucpii_pam.ui.view.matakuliah.HomeMkView
import com.example.ucpii_pam.ui.view.matakuliah.InsertMkView
import com.example.ucpii_pam.ui.view.matakuliah.UpdateMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanUtama.route,
    ) {
        composable(route = DestinasiHalamanUtama.route) {
            HalamanUtamaView(
                onMataKuliahButton = { navController.navigate(DestinasiHomeMk.route) },
                onDosenButton = { navController.navigate(DestinasiHomeDsn.route) }
            )
        }
        composable(
            route = DestinasiHomeDsn.route
        ){
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsertDsn.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertDsn.route
        ){
            InsertDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(route = DestinasiHomeMk.route) {
            HomeMkView(
                onDetailClick = {kodeMk ->
                    navController.navigate("${DestinasiDetailMk.route}/$kodeMk")
                    println(
                        "PengelolaHalaman: kodeMk = $kodeMk"
                    )
                },
                onAddMk = {
                    navController.navigate(DestinasiInsertMk.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsertMk.route
        ) {
            InsertMkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            DestinasiDetailMk.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailMk.kodeMk){
                    type = NavType.StringType
                }
            )
        ) {
            val kodeMk = it.arguments?.getString(DestinasiDetailMk.kodeMk)

            kodeMk?.let { kodeMk ->
                DetailMkView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMk.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateMk.routeWithArgs,
            arguments = listOf(
                navArgument((DestinasiUpdateMk.kodeMk)){
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}