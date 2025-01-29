enum class RangoIMC(val descripcion: String) {
    PESO_INSUFICIENTE("peso insuficiente"),
    PESO_SALUDABLE("peso saludable"),
    SOBREPESO("sobrepeso"),
    OBESIDAD("obesidad")
}

class Persona(private val peso: Double, private val altura: Double, private val nombre: String? = null) {
    private val imc: Double
        get() = peso / (altura * altura)

    fun saludar(): String = "Hola, soy ${nombre ?: "Sin nombre"}!"

    fun alturaEncimaMedia(): Boolean = altura >= 1.75

    fun pesoEncimaMedia(): Boolean = peso >= 70

    private fun obtenerDescImc(): RangoIMC = when {
        imc < 18.5 -> RangoIMC.PESO_INSUFICIENTE
        imc in 18.5..24.9 -> RangoIMC.PESO_SALUDABLE
        imc in 25.0..29.9 -> RangoIMC.SOBREPESO
        else -> RangoIMC.OBESIDAD
    }

    fun obtenerDesc(): String {
        val alturaDesc = if (alturaEncimaMedia()) "por encima de la media" else "por debajo de la media"
        val pesoDesc = if (pesoEncimaMedia()) "por encima de la media" else "por debajo de la media"
        return "${nombre ?: "Sin nombre"} con una altura de ${"%.2f".format(altura)}m ($alturaDesc) y un peso ${"%.1f".format(peso)}kg ($pesoDesc) tiene un IMC de ${"%.2f".format(imc)} (${obtenerDescImc().descripcion})."
    }
}

fun main() {
    val personas = listOf(
        Persona(55.0, 1.65, "Laura"),
        Persona(80.0, 1.85, "Carlos"),
        Persona(95.0, 1.75, "Ana"),
        Persona(68.0, 1.70, "Luis"),
        Persona(72.0, 1.80, "Elena")
    )

    for (persona in personas) {
        println(persona.saludar())
        println(persona.obtenerDesc())
        println()
    }
}
