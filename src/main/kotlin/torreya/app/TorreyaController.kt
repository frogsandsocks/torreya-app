package torreya.app

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class TorreyaController {


    var ports: ObservableList<String> = FXCollections.observableArrayList(listOf<String>())
    var portSelected = SimpleStringProperty()

    val serialCommunicator = SerialCommunicator()


    val app = TorreyaApp()

    init {

        serialCommunicator.listAllPorts()
        ports = FXCollections.observableArrayList<String>(serialCommunicator.listAllPorts())
    }


    fun createSerialCommunicator(): PrinterControl {

        val printer = PrinterControl()

        val portName = controller
                .portSelected
                .toString()
                .removeSurrounding("StringProperty [value: ", "]")

        serialCommunicator.createInstance(
                portName,
                115200)
        { message: String -> app.parser(message) }

        return printer
    }
}