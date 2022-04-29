package ar.edu.unahur.obj2.semillas

class Inta () {
    private  val parcelas = mutableListOf<Parcela>()

    fun parcelasTotales(): Int = parcelas.size

    fun agregarParcela(parcela: Parcela){
        parcelas.add(parcela)
    }



    fun parcelaConMejorAsociacion(): Parcela? {
        val parcelaMasAutosustentable = parcelas.filter{ it.totalDePlantas() > 4 }.maxByOrNull { it.porcentajeDeBienAsociada() }
        return parcelaMasAutosustentable
    }


    fun plantasPorParcela(): MutableList<Int> {
        val listaDePlantasPorParcela = mutableListOf<Int>()
        parcelas.map { listaDePlantasPorParcela.add(it.totalDePlantas()) }
        return listaDePlantasPorParcela

    }

    fun promedioDePlantas()=
        if (this.parcelasTotales()==0) {
            0
        }
        else {
            this.plantasPorParcela().div( this.parcelasTotales())
        }

    fun getParcelas() = parcelas
}

operator fun Any.div(parcelasTotales: Int): Int = this/ parcelasTotales
