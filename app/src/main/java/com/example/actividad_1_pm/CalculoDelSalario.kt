package com.example.actividad_1_pm

fun calcularDatos(salarioBruto: Double): Triple<Double, Double, Double> {
    val retencionIRPF = 0.15  // 15%
    val deducciones = 2000.0  // Deducción simulada
    val irpf = salarioBruto * retencionIRPF
    val salarioNeto = salarioBruto - irpf - deducciones

    return Triple(salarioNeto, irpf, deducciones)

}