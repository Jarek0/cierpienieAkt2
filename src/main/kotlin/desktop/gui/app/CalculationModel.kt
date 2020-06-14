package desktop.gui.app

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.Alert
import javafx.scene.control.Button
import tornadofx.*
import kotlin.math.PI
import kotlin.math.pow

sealed class CalculationModel: ViewModel() {

    protected val result = bind { SimpleDoubleProperty() }

    abstract fun createCalculationFields(): Fieldset

    fun createResultField(): Fieldset {
        val aFieldSet = Fieldset()
        aFieldSet.field("Wynik: ") {
            label(result)
        }
        return aFieldSet
    }

    fun createActionButton(): Button {
        val button = Button("Oblicz")
        button.isDefaultButton = true
        button.action {
            this.commit {
                this.calculate()
            }
        }
        return button
    }

    abstract fun calculate()

    fun showResult(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message)
        alert.title = "Wyniki"
        alert.headerText = null
        alert.showAndWait()
    }
}

class RectangleArea: CalculationModel() {
    private val a = bind { SimpleDoubleProperty() }
    private val b = bind { SimpleDoubleProperty() }

    override fun createCalculationFields(): Fieldset {
        val aFieldSet = Fieldset()
        aFieldSet.field("Podaj a") {
            textfield(a) {
                required()
                filterInput { it.controlNewText.isDouble() }
            }
        }
        aFieldSet.field("Podaj b") {
            textfield(b) {
                required()
                filterInput { it.controlNewText.isDouble() }
            }
        }
        return aFieldSet
    }

    override fun calculate() {
        result.value = a.value.toDouble() * b.value.toDouble()
        showResult("Pole prostokata: ${result.value.toDouble()}")
    }
}

class TriangleArea: CalculationModel() {
    private val a = bind { SimpleDoubleProperty() }
    private val h = bind { SimpleDoubleProperty() }

    override fun createCalculationFields(): Fieldset {
        val aFieldSet = Fieldset()
        aFieldSet.field("Podaj a") {
            textfield(a) {
                required()
                filterInput { it.controlNewText.isDouble() }
            }
        }
        aFieldSet.field("Podaj h") {
            textfield(h) {
                required()
                filterInput { it.controlNewText.isDouble() }
            }
        }
        return aFieldSet
    }

    override fun calculate() {
        result.value = 0.5 * a.value.toDouble() * h.value.toDouble()
        showResult("Pole trojkata: ${result.value.toDouble()}")
    }
}

abstract class SphereCalculation: CalculationModel() {
    protected val r = bind { SimpleDoubleProperty() }

    override fun createCalculationFields(): Fieldset {
        val aFieldSet = Fieldset()
        aFieldSet.fieldset {
            field("Podaj r:") {
                textfield(r) {
                    required()
                    filterInput { it.controlNewText.isDouble() }
                }
            }
        }
        return aFieldSet
    }

}

class SphereField: SphereCalculation() {
    override fun calculate() {
        result.value = 4 * PI * r.value.toDouble().pow(2.0)
        showResult("Pole kuli: ${result.value.toDouble()}")
    }
}

class SphereVolume: SphereCalculation() {
    override fun calculate() {
        result.value = (4.0/3.0) * PI * r.value.toDouble().pow(3.0)
        showResult("Objetosc trojkata: ${result.value.toDouble()}")
    }
}