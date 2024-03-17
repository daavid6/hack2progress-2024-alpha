package com.example.firelight.data

class Riesgos {

    companion object {
        fun riesgoActual(temperatura: Double, viento: Double, humedad: Double, sequedad: SequedadVegetacion): Double {

            val t = riesgoTemperatura(temperatura) * (1/3.5)
            val v = riesgoViento(viento) * (1/2)
            val h = riesgoHumedad(humedad) * (1/3)
            val s = riesgoSequedadVegetacion(sequedad) * (1/4.5)

            return (t + v + s - h)
        }


        private fun riesgoTemperatura(temperatura: Double): Double {
            var riesgo = 0.0

            when {
                temperatura < 10.0 -> riesgo = 0.0
                temperatura in 10.0..20.0 -> riesgo = 1.0
                temperatura in 20.0..25.0 -> riesgo = 2.0
                temperatura in 25.0..30.0 -> riesgo = 3.0
                temperatura in 30.0..35.0 -> riesgo = 4.0
                temperatura in 35.0..40.0 -> riesgo = 5.0
                temperatura in 40.0..45.0 -> riesgo = 6.0
                temperatura > 45.0 -> riesgo = 7.0
            }

            return riesgo/7.0
        }


        private fun riesgoViento(viento: Double): Double {
            var riesgo = 0.0

            when {
                viento < 10.0 -> riesgo = 0.0
                viento in 10.0..15.0 -> riesgo = 1.0
                viento in 20.0..25.0 -> riesgo = 2.0
                viento in 25.0..45.0 -> riesgo = 3.0
                viento in 45.0..65.0 -> riesgo = 4.0
                viento in 65.0..90.0 -> riesgo = 5.0
                viento > 90.0 -> riesgo = 6.0
            }

            return riesgo/6.0
        }


        private fun riesgoHumedad(humedad: Double): Double {
            var riesgo = 0.0

            when {
                humedad < 10.0 -> riesgo = 0.0
                humedad in 10.0..20.0 -> riesgo = 1.0
                humedad in 20.0..30.0 -> riesgo = 2.0
                humedad in 30.0..40.0 -> riesgo = 3.0
                humedad in 40.0..50.0 -> riesgo = 4.0
                humedad in 50.0..60.0 -> riesgo = 5.0
                humedad in 60.0..70.0 -> riesgo = 6.0
                humedad in 70.0..80.0 -> riesgo = 7.0
                humedad in 80.0..90.0 -> riesgo = 8.0
                humedad > 90.0 -> riesgo = 9.0
            }

            return riesgo/9.0
        }


        private fun riesgoSequedadVegetacion(sequedad: SequedadVegetacion): Double {
            val riesgo = when (sequedad) {
                SequedadVegetacion.MUYHUMEDO -> -1.0
                SequedadVegetacion.HUMEDO -> 0.0
                SequedadVegetacion.MEDIO -> 1.0
                SequedadVegetacion.SECO -> 2.0
            }

            return riesgo/2.0
        }
    }
}
