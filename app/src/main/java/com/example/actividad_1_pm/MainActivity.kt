package com.example.actividad_1_pm


import android.R
import android.R.color.white
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.actividad_1_pm.ui.theme.Actividad_1PMTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad_1PMTheme {
                val navController = rememberNavController()
                val viewModel: SalarioViewModel = viewModel()

                NavHost(navController = navController, startDestination = "Inicio") {
                    composable("Inicio") {
                        Myapp(
                            viewModel = viewModel,
                            navigateToResult = {
                                viewModel.calcularSalario()
                                navController.navigate("Result")
                            }
                        )
                    }
                    composable("Result") {
                        SecondScreen(
                            viewModel = viewModel,
                            navigateToInicio = {
                                navController.navigate("Inicio")
                            })
                    }


                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Myapp(
    viewModel: SalarioViewModel = viewModel(),
    navigateToResult: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        //la parte de arriba
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    Color(0xFF6B6054)//CBBBA0FF
                ),

                title = {
                    Text(
                        modifier = Modifier,

                        text = "Formulario de Datos", color = Color.White,
                    )
                }
            )
        },
        //el medio

        floatingActionButton = {

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // NEW: Clear Button
                FloatingActionButton(
                    onClick = { viewModel.limpiarDatos() },
                    containerColor = Color(0xFF6B6054)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Limpiar datos")
                }

                // Calculate Button (existing)
                FloatingActionButton(onClick = { navigateToResult() }, containerColor = Color(0xFF6B6054)) {

                    Icon(Icons.Default.Send, contentDescription = "Calcular")


                }
        }  }

    ) { innerPadding -> //Fin del Scaffold

        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),

            color = Color(0xFFCBBBA0),
            //CBBBA0FF, 6B6054FF


            ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                TextField(
                    value = state.grupoProfesional,
                    onValueChange = { viewModel.upGrupoProfesional(it.trim()) },
                    label = { Text("Grupo Profesional") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.edad,
                    onValueChange = { viewModel.upEdad(it.trim()) },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.salarioBrutoAnual,
                    onValueChange = { viewModel.upSalarioBrutoAnual(it.trim()) },
                    label = { Text("Salario Bruto Anual") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.numeroDePagas,
                    onValueChange = { viewModel.upNumeroDePagas(it.trim()) },
                    label = { Text("Número De Pagas") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.gradoDeDiscapacidad,
                    onValueChange = { viewModel.upGradoDeDiscapacidad(it.trim()) },
                    label = { Text("Grado De Discapacidad") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.estadoCivil,
                    onValueChange = { viewModel.upEstadoCivil(it.trim()) },
                    label = { Text("Estado Civil") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.numeroDeHijos,
                    onValueChange = { viewModel.upNumeroDeHijos(it.trim()) },
                    label = { Text("Número De Hijos") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(10.dp))

            }

        }

    }

}











