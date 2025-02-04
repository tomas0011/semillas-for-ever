package ar.edu.unahur.obj2.semillas

abstract class Planta(
    private val fechaDeObtencion: Int,
    open val altura: Double
) {
    open fun horasDeSolTolera(): Int {
        return 7
    }

    fun esFuerte(): Boolean {
        return horasDeSolTolera() > 9
    }

    abstract fun daNuevasSemillas(): Boolean

    open fun espacioQueOcupa(): Double {
        return 8.0
    }

    abstract fun esParcelaIdeal(parcela: Parcela): Boolean

    fun seAsociaBien(parcela: Parcela): Boolean {
        return if (parcela.tipoDeParcela === TiposDeParcela.ECOLOGICA) {
            (!parcela.tieneComplicaciones()) && this.esParcelaIdeal(parcela)
        } else {
            parcela.totalDePlantas() < 2 && this.esFuerte()
        }
    }
}
