@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.actividad_1_pm

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BadgeDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults.containerColor
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale


@Composable
fun SecondScreen(
    viewModel: SalarioViewModel,
    navigateToInicio: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val formatter = NumberFormat.getCurrencyInstance(Locale("es", "ES"))
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    Color(0xFF6B6054)//CBBBA0FF
                ),
                title = {
                    Text("Resultados del Cálculo", color = Color.White)
                }
            )
        },
       /* bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Calculadora de Salario Neto",
                )
            }
        },*/
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
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Resultados",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ResultCard(
                title = "Salario Bruto Anual",
                value = formatter.format(state.salarioBrutoAnual.toDoubleOrNull() ?: 0.0)

            )

            ResultCard(
                title = "Retención IRPF (15%)",
                value = formatter.format(state.irpf)
            )

            ResultCard(
                title = "Deducciones",
                value = formatter.format(state.deducciones)
            )

            ResultCard(
                title = "Salario Neto",
                value = formatter.format(state.salarioNeto),
                highlighted = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navigateToInicio()},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B6054))
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                Text(text = " Finalizar", modifier = Modifier.padding(start = 8.dp))

            }
        }

    }
}
}
@Composable
fun ResultCard(
    title: String,
    value: String,
    highlighted: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (highlighted)
                Color(0xFFF5ECDD)
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (highlighted) FontWeight.Bold else FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = if (highlighted)
                    Color(0xFF6A3B02)
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



