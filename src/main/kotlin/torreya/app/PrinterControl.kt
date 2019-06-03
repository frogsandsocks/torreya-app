package torreya.app
import torreya.app.controller


class PrinterControl {


    var currentXPosition = 0
    val serialCommunicator = controller.serialCommunicator


    init {

        var hotbedOn = true
        var extruderOn = true

        var hotbedTemperature = 0
        var extruderTemperature = 0
    }

    fun extrudeOut() {

        serialCommunicator.println("F800")
    }

    fun extrudeIn() {

        serialCommunicator.println("F1800")
    }

    fun moveX(toRight: Boolean) {

        serialCommunicator.println("G91\nG1 " + (if(!toRight) "-" else "") + "X1 F3600")
    }

    fun moveY() {}

    fun moveZ() {}

    fun moveHome() {

        serialCommunicator.println("G28")
    }

    fun hotbedOn(state: Boolean) {}

    fun extrudeOn(state: Boolean) {}

    fun extrudeTemperatureUpdate(temperature: Int) {

        serialCommunicator.println("M104 S$temperature")
    }

    fun hotbedTemperatureUpdate(temperature: Int) {

        serialCommunicator.println("M140 S$temperature")
    }
}