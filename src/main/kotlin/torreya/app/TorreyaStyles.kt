package torreya.app

import javafx.scene.paint.Color
import tornadofx.*

class TorreyaStyles : Stylesheet() {

    companion object {

        val buttonArrow by cssclass()

        val boxCoordinates           by cssclass()
        val textFieldCoordinates     by cssclass()
        val textFieldCoordinatesName by cssclass()

        val boxTemperatureConfiguration by cssclass()
        val boxTempConfigurationLabel by cssclass()
        val boxTempConfigurationExtruder by cssclass()
    }

    init {

        buttonArrow {

            backgroundInsets += box(5.px)

            minWidth  = 80.px
            minHeight = 80.px

            /*
            textFill = Color.WHITE
            backgroundColor += Color.DARKGRAY
            */

            fontFamily = "monospace"
            fontSize   = 18.px
        }


        val boxTextLabel = mixin {

            fontFamily = "monospace"
            fontSize   = 18.px

            padding = box(6.px)
        }


        boxCoordinates {

            padding = box(5.px, 5.px, 10.px, 5.px)
        }


        textFieldCoordinatesName { +boxTextLabel }


        textFieldCoordinates {

            +boxTextLabel

            borderColor += box(Color.GREY)
            backgroundColor += Color.SLATEGREY
            textFill = Color.WHITE
        }



        boxTemperatureConfiguration {

            padding = box(10.px)
            borderColor += box(
                    Color.LIGHTGREY,
                    Color.WHITESMOKE,
                    Color.WHITESMOKE,
                    Color.WHITESMOKE
            )
        }


        boxTempConfigurationLabel {

            padding = box(5.px)
        }

    }
}