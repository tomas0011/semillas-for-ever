package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class EstadisticasINTATest: DescribeSpec ({
    describe("Creacion de parcelas, creacion de plantas y agregar plantas a las parcelas"){
        val mentaGrande = Menta(2021, 1.0)
        val mentaNormal = Menta(2021, 0.5)
        val mentaChica = Menta(2021, 0.3)

        val quinoaGrande = Quinoa(2010,1.0,.9)
        val quinoaNormal = Quinoa(2006,0.6,.4)
        val quinoaChica = Quinoa(2022,0.4,.2)

        val peperinaGrande = Peperina(2021, 1.0)
        val peperinaNormal = Peperina(2021, 0.5)
        val peperinaChica = Peperina(2021, 0.3)

        val sojaGrande = Soja(2021, 1.2)
        val sojaNormal = Soja(2021, .8)
        val sojaChica = Soja(2021, .4)
        val sojaVieja = Soja(2006, .8)

        val parcela1 = Parcela(
            4,
            4,
            6,

            mutableListOf(
                mentaGrande,
                quinoaChica,
                peperinaNormal,
                mentaChica,
                sojaGrande,
                sojaVieja
            ),
            TiposDeParcela.ECOLOGICA
        )

        val parcela2 = Parcela(
            2,
            6,
            8,
            mutableListOf(
                mentaNormal,
                quinoaGrande,
                quinoaNormal,
                peperinaChica,
                peperinaGrande
            )
        )

        val parcela3 = Parcela(
            10,4, 12,
            mutableListOf(
                sojaNormal,
                sojaChica
            )
        )

        val Inta = Inta()

        Inta.agregarParcela(parcela1)
        Inta.agregarParcela(parcela2)
        Inta.agregarParcela(parcela3)


        it ("El promedio de plantas por parcela deberia ser 4, ya que tiene 3 parcelas y estas tienen 6, 5 y 2 planas (13/3 = 4.33). Se redondea hacia abajo "){

            Inta.parcelasTotales().shouldBe(3)
            Inta.plantasPorParcela().shouldBe(listOf(6,5,2))
            Inta.promedioDePlantas().shouldBe(4)
        }

        it ("La parcela más autosustentable deberia ser la que tiene más de 4 plantas y tiene el mayor porcentaje de plantas 'bien asociadas' "){
            val parcelas = Inta.getParcelas()
            parcelas.filter{it.totalDePlantas() > 4}.shouldBe(listOf(parcela1,parcela2))
            Inta.parcelaConMejorAsociacion().shouldBe(parcela1)
            Inta.parcelaConMejorAsociacion().shouldBe(parcela1)
        }
    }


})
