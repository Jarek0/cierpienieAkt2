package desktop.gui.app

import tornadofx.*

class Styles : Stylesheet() {

    init {
        geometryCalculatorScreen {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }
    }

    companion object {
        val geometryCalculatorScreen by cssclass()
    }
}