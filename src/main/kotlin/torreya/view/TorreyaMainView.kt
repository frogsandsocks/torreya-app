package torreya.view

import javafx.scene.control.Button
import javafx.scene.layout.BorderStrokeStyle

import javafx.scene.paint.Color
import tornadofx.*
import torreya.app.PrinterControl
import torreya.app.TorreyaStyles
import torreya.app.controller

import java.lang.NumberFormatException


class TorreyaMainView : View("Torreya") {

    private val printer = controller.createSerialCommunicator()


    override val root = vbox {


        hbox {

            gridpane {

                style {
                    padding = box(10.px)
                    borderColor += box(vertical = Color.WHITESMOKE, horizontal = Color.LIGHTGREY)
                }


                button("↑") { gridpaneConstraints { columnRowIndex(2, 1) } }

                button("X\n←") {

                    gridpaneConstraints { columnRowIndex(1, 2) }
                    action { printer.moveX(false) }
                }

                button("X\n→") {
                    gridpaneConstraints { columnRowIndex(3, 2) }
                    action { printer.moveX(true) }
                }

                button("↓") { gridpaneConstraints { columnRowIndex(2, 3) } }

                button("HOME\nALT") {
                    gridpaneConstraints { columnRowIndex(2, 2) }
                    action { printer.moveHome() }
                }

                button("EXT\n ⇡") {
                    gridpaneConstraints { columnRowIndex(1, 1) }
                    action { printer.extrudeIn() }
                }

                button("EXT\n ⇣") {
                    gridpaneConstraints { columnRowIndex(1, 3) }
                    action { printer.extrudeOut() }
                }


                children.filter { it is Button }.addClass(TorreyaStyles.buttonArrow)

            }

            vbox {

                style { padding = box(10.px) }


                hbox {

                    vbox {

                        addClass(TorreyaStyles.boxCoordinates)

                        label("X position") { addClass(TorreyaStyles.textFieldCoordinatesName) }
                        label("12.34") {
                            addClass(TorreyaStyles.textFieldCoordinates)
                            useMaxWidth = true
                        }
                    }

                    button("HOME\nX") { addClass(TorreyaStyles.buttonArrow) }
                }


                hbox {

                    vbox {

                        addClass(TorreyaStyles.boxCoordinates)

                        label("Y position") { addClass(TorreyaStyles.textFieldCoordinatesName) }
                        label("56.78") {
                            addClass(TorreyaStyles.textFieldCoordinates)
                            useMaxWidth = true
                        }
                    }

                    button("HOME\nY") { addClass(TorreyaStyles.buttonArrow) }
                }


                hbox {

                    vbox {

                        addClass(TorreyaStyles.boxCoordinates)

                        label("Z position") { addClass(TorreyaStyles.textFieldCoordinatesName) }
                        label("90.12") {
                            addClass(TorreyaStyles.textFieldCoordinates)
                            useMaxWidth = true
                        }
                    }

                    button("HOME\nZ") { addClass(TorreyaStyles.buttonArrow) }
                }
            }
        }


        hbox {

            addClass(TorreyaStyles.boxTemperatureConfiguration)
            useMaxWidth = true

            vbox {

                useMaxWidth = true

                hbox {

                    useMaxWidth = true
                    style { padding = box(5.px) }

                    label("Bed       ") { addClass(TorreyaStyles.textFieldCoordinatesName) }
                    textfield("???") {

                        addClass(TorreyaStyles.textFieldCoordinates)

                        textProperty().addListener { _, _, new ->

                            try {
                                printer.extrudeTemperatureUpdate(new.toInt())
                            } catch (e: NumberFormatException) {
                            }


                        }
                    }
                }


                hbox {

                    useMaxWidth = true

                    style {

                        minWidth = 390.px

                        padding = box(20.px)

                        borderColor += box(Color.GREY)
                        borderStyle += BorderStrokeStyle.DASHED
                    }
                }
            }

            button("HBED\nON") { addClass(TorreyaStyles.buttonArrow) }
        }


        hbox {

            useMaxWidth = true
            addClass(TorreyaStyles.boxTemperatureConfiguration)

            vbox {

                useMaxWidth = true

                hbox {

                    useMaxWidth = true
                    style { padding = box(5.px) }

                    label("Extruder  ") { addClass(TorreyaStyles.textFieldCoordinatesName) }
                    textfield("???") {

                        addClass(TorreyaStyles.textFieldCoordinates)

                        textProperty().addListener { _, _, new ->

                            try {
                                printer.hotbedTemperatureUpdate(new.toInt())
                            } catch (e: NumberFormatException) {
                            }


                        }
                    }
                }

                hbox {

                    useMaxWidth = true

                    style {

                        minWidth = 390.px

                        padding = box(20.px)

                        borderColor += box(Color.GREY)
                        borderStyle += BorderStrokeStyle.DASHED
                    }
                }
            }

            button("EXT\nON") { addClass(TorreyaStyles.buttonArrow) }
        }

    }
}
