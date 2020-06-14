package desktop.gui.app

import javafx.stage.Stage
import tornadofx.*

class GeometryCalculatorApp : App(GeometryCalculatorScreen::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.onCloseRequest = ConfirmCloseEventHandler
    }
}

fun main(args: Array<String>) {
    launch<GeometryCalculatorApp>(args)
}