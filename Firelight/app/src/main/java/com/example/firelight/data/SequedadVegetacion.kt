package com.example.firelight.data

import kotlin.random.Random

enum class SequedadVegetacion {
    SECO,
    MEDIO,
    HUMEDO,
    MUYHUMEDO;

    companion object {
        fun sequedadRandom(): SequedadVegetacion {
            val values = SequedadVegetacion.entries.toTypedArray()
            return values[Random.nextInt(values.size)]
        }
    }
}