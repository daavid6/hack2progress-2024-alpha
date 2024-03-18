package com.example.firelight.data

enum class EstadosSemaforo(name: String) {
    VERDE("Verde"),
    AMBAR("Ambar"),
    ROJO("Rojo");

    companion object {
        fun obtenColorSemaforo(riesgo: Double): EstadosSemaforo? {
            val color = when (riesgo) {
                in 0.0..0.5 -> VERDE
                in 0.5..0.8 -> AMBAR
                in 0.8..1.0 -> ROJO
                else -> null
            }

            return color
        }
    }
}