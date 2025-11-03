package com.example.actividad_1_pm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SalarioState(
    //Datos nesesarios para el calculo del salario
    val grupoProfesional: String = "",
    val edad: String = "",
    val salarioBrutoAnual: String = "",
    val numeroDePagas: String = "",
    val gradoDeDiscapacidad: String = "",
    val estadoCivil: String = "",
    val numeroDeHijos: String = "",
    val salarioNeto: Double = 0.0,
    val irpf: Double = 0.0,
    val deducciones: Double = 0.0
)

//usamos el ViewModel
class SalarioViewModel : ViewModel() {
    private val _state = MutableStateFlow(SalarioState())
    val state: StateFlow<SalarioState> = _state.asStateFlow()

    fun upGrupoProfesional(value: String) {
        _state.value = _state.value.copy(grupoProfesional = value)
    }

    fun upEdad(value: String) {
        _state.value = _state.value.copy(edad = value)
    }

    fun upSalarioBrutoAnual(value: String) {
        _state.value = _state.value.copy(salarioBrutoAnual = value)
    }

    fun upNumeroDePagas(value: String) {
        _state.value = _state.value.copy(numeroDePagas = value)
    }

    fun upGradoDeDiscapacidad(value: String) {
        _state.value = _state.value.copy(gradoDeDiscapacidad = value)
    }

    fun upEstadoCivil(value: String) {
        _state.value = _state.value.copy(estadoCivil = value)
    }

    fun upNumeroDeHijos(value: String) {
        _state.value = _state.value.copy(numeroDeHijos = value)
    }
    fun limpiarDatos() {
        _state.value = SalarioState()
    }

    fun calcularSalario() {
        val salarioBruto = _state.value.salarioBrutoAnual.toDoubleOrNull() ?: 0.0
        //si el .toDoubleOrNull que es una convercion falla Elvis pone en su lugar 0.0
        val (salarioNeto, irpf, deducciones) = calcularDatos(salarioBruto)

        _state.value = _state.value.copy(
            salarioNeto = salarioNeto,
            irpf = irpf,
            deducciones = deducciones
        )
    }
}
