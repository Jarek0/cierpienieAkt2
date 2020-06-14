package desktop.gui.app

import desktop.gui.app.Styles.Companion.geometryCalculatorScreen
import javafx.scene.layout.VBox
import javafx.stage.WindowEvent
import tornadofx.*
import tornadofx.FX.Companion.getPrimaryStage

class GeometryCalculatorScreen : View() {

    private var model: CalculationModel = SphereVolume()
    private var calculationFields = model.createCalculationFields()
    private var resultField = model.createResultField()
    private var actionButton = model.createActionButton()

    override val root = VBox()

    init {
        title = "Objetosc kuli"
        with(root) {
            menubar {
                menu("Plik") {
                    item("Pole prostokata").action {
                        replaceCalculationModel(RectangleArea())
                        title = "Pole prostokata"
                    }
                    item("Pole trojkota").action {
                        replaceCalculationModel(TriangleArea())
                        title = "Pole trojkota"
                    }
                    item("Pole kuli").action {
                        replaceCalculationModel(SphereField())
                        title = "Pole kuli"
                    }
                    item("Objetosc kuli").action {
                        replaceCalculationModel(SphereVolume())
                        title = "Objetosc kuli"
                    }
                    separator()
                    item("Zakoncz").action {
                        fireEvent(WindowEvent(
                                getPrimaryStage(),
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        ))
                    }
                }
                menu("Edycja") {
                }
                menu("Format") {
                }
                menu("Pomoc") {
                    item("Informacje")
                }
            }
            form {
                addClass(geometryCalculatorScreen)

                this.add(calculationFields)

                button("Oblicz") {
                    isDefaultButton = true

                    action {
                        model.commit {
                            model.calculate()
                        }
                    }
                }

                this.add(resultField)

                button("Czy zakonczyc?") {
                    action {
                        fireEvent(WindowEvent(
                                getPrimaryStage(),
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        ))
                    }
                }
            }
        }
    }

    private fun replaceCalculationModel(newModel: CalculationModel) {
        replaceCalculationFields(newModel)
        replaceResultField(newModel)
        replaceActionButton(newModel)
        this.model = newModel
    }

    private fun replaceCalculationFields(model: CalculationModel) {
        val newCalculationFields = model.createCalculationFields()
        calculationFields.replaceWith(newCalculationFields)
        calculationFields = newCalculationFields
    }

    private fun replaceResultField(model: CalculationModel) {
        val newResultField = model.createResultField()
        resultField.replaceWith(newResultField)
        resultField = newResultField
    }

    private fun replaceActionButton(model: CalculationModel) {
        val newActionButton = model.createActionButton()
        actionButton.replaceWith(newActionButton)
        actionButton = newActionButton
    }

    override fun onDock() {
        model.validate(decorateErrors = false)
    }
}