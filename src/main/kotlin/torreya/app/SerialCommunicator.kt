package torreya.app

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import jssc.*
import tornadofx.*
import java.lang.Exception


class SerialCommunicator {

    val serialPortProperty = SimpleObjectProperty<SerialPort>()
    var serialPort by serialPortProperty


    val lineBufferProperty = SimpleStringProperty("")
    var lineBuffer by lineBufferProperty



    fun listAllPorts(): List<String> {

        var ports = listOf<String>()

        for (port in SerialPortList.getPortNames()) {

            ports += (port ?: throw Exception("AAAAAAAAAAAAAAAAAAAAAA"))
        }

        return ports
    }



    /**
     * Initializes class
     * param {String}    port            Path to the port pseudodevice
     * param {Function}  messageHandler  Message callback lambda
     */
    fun createInstance(port: String, baudrate: Number, messageHandler: (String) -> Unit) {

        /**
         * Filling SerialPortEventListener interface
         */
        class PerByteReader : SerialPortEventListener {

            override fun serialEvent(event: SerialPortEvent) {

                try {

                    var it = 0
                    while (it < event.eventValue) {

                        // Reading string from port, then converting it into char
                        val rxChar = serialPort!!.readString(1).toCharArray()[0]

                        // Checking is it a NL character?
                        if (rxChar != '\n') {

                            // Apend character to buffer
                            lineBuffer += rxChar


                        } else {

                            // Full line received, calling message handler
                            messageHandler(lineBuffer)

                            // Reset buffer
                            lineBuffer = ""

                        }

                        it += 1;
                    }

                } catch (ex: SerialPortException) {
                    println(ex)
                }

            }


        }

        // Creates instance of the serial port
        serialPort = SerialPort(port)

        try {

            // Opening the port
            serialPort!!.openPort()

            // Setting UART preferences
            serialPort!!.setParams(baudrate.toInt(), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE)

            // Adding per-char event listener
            serialPort!!.addEventListener(PerByteReader(), SerialPort.MASK_RXCHAR)


        } catch (ex: SerialPortException) {

            // Do something with the error
            println(ex)

        }

    }


    fun println (str: String) {

        serialPort!!.writeString("$str\n")
    }


    /*
    fun listAllPorts() {

        val ports = FXCollections.observableArrayList<String>()

        for (port in SerialPortList.getPortNames()) {

            ports += port
        }
    }

     */
}
