package desktop.gui.app

import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.stage.WindowEvent
import java.util.*

object ConfirmCloseEventHandler: EventHandler<WindowEvent> {

    override fun handle(event: WindowEvent?) {
        val exitConfirmation = Alert(Alert.AlertType.CONFIRMATION, "Zakonczyc program?")
        exitConfirmation.title = "Uwaga"
        exitConfirmation.headerText = null
        val exitButton: Button = exitConfirmation.dialogPane.lookupButton(
                ButtonType.OK
        ) as Button
        exitButton.text = "Tak"
        val closeConfirmationButton: Button = exitConfirmation.dialogPane.lookupButton(
                ButtonType.CANCEL
        ) as Button
        closeConfirmationButton.text = "Nie"
        val closeResponse: Optional<ButtonType> = exitConfirmation.showAndWait()
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event?.consume()
        }
    }

}