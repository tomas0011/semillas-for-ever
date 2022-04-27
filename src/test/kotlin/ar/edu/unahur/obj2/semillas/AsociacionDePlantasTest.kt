package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe

class AsociacionDePlantasTest: DescribeSpec ({
    describe("Asociacion de plantas") {
        val mentaGrande = Menta(2021, 1.0)
        val sojaChica = Soja(2021, 0.4)
        val sojaFuerte = Soja(2021, 1.5)

        describe("Parcelas ecologicas") {
            val parcelaEcologica = Parcela(
                10,
                10,
                10,
                mutableListOf(),
                TiposDeParcela.ECOLOGICA
            )

            it("La planta se asocia bien si la parcela no tiene complicaciones y es ideal para la planta") {
                parcelaEcologica.tieneComplicaciones().shouldBeFalse()
                mentaGrande.esParcelaIdeal(parcelaEcologica).shouldBeTrue()
                mentaGrande.seAsociaBien(parcelaEcologica).shouldBeTrue()
            }

            it("La planta NO se asocia bien si la parcela tiene complicaciones") {
                parcelaEcologica.plantar(sojaChica)
                parcelaEcologica.tieneComplicaciones().shouldBeTrue()
                mentaGrande.seAsociaBien(parcelaEcologica).shouldBeFalse()
            }

            it("La planta NO se asocia bien si la parcela no es ideal para la planta") {
                sojaChica.esParcelaIdeal(parcelaEcologica).shouldBeFalse()
                sojaChica.seAsociaBien(parcelaEcologica).shouldBeFalse()
            }
        }

        describe("Parcelas industriales") {
            val parcelaIndustrial = Parcela(
                10,
                10,
                10,
                mutableListOf(),
                TiposDeParcela.INDUSTRIAL
            )

            it("La planta se asocia bien a la parcela si en esta hay un como maximo 2 plantas y esta planta sea fuerte") {
                parcelaIndustrial.totalDePlantas().shouldBeLessThan(2)
                sojaFuerte.esFuerte().shouldBeTrue()
                sojaFuerte.seAsociaBien(parcelaIndustrial).shouldBeTrue()
            }

            it("La planta NO se asocia bien a la parcela si en esta hay mas de 2 plantas") {
                parcelaIndustrial.totalDePlantas().shouldBeGreaterThan(2)
                mentaGrande.seAsociaBien(parcelaIndustrial).shouldBeFalse()
            }

            it("La planta NO se asocia bien a la parcela si esta planta no es fuerte") {
                mentaGrande.esFuerte().shouldBeFalse()
                mentaGrande.seAsociaBien(parcelaIndustrial).shouldBeFalse()
            }
        }
    }
})