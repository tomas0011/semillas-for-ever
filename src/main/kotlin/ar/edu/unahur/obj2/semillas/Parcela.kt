package ar.edu.unahur.obj2.semillas

class Parcela(
    private val ancho: Int,
    private val largo: Int,
    val horasDeSolQueRecibe: Int,
    private val plantas: MutableList<Planta>,
    val tipoDeParcela: TiposDeParcela = TiposDeParcela.ECOLOGICA
) {
    fun superficie(): Int = ancho * largo

    fun cantidadMaximaDePlantas(): Int {
        return if(ancho > largo) {
            superficie() / 5
        } else {
            (superficie() / 3) + largo
        }
    }

    fun tieneComplicaciones(): Boolean {
        return plantas.any { it.horasDeSolTolera() < horasDeSolQueRecibe }
    }

    fun plantar(planta: Planta) {
        if (cantidadMaximaDePlantas() == totalDePlantas()) {
            throw Exception("Cantidad maxima de plantas por parcela superada.")
        }
        plantas.add(planta)
    }

    fun totalDePlantas(): Int = plantas.size

    fun plantas(): List<Planta> = plantas


    fun cantidadBienAsociadas() {
        plantas.count { it.esParcelaIdeal(this)}
    }

    fun porcentajeDeBienAsociada(): Int {
        return this.cantidadBienAsociadas().div( this.totalDePlantas())
    }


}
