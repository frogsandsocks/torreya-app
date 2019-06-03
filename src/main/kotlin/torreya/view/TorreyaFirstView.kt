package torreya.view

import tornadofx.*
import torreya.app.TorreyaController
import torreya.app.controller

class TorreyaFirstView : View("Torreya") {

    override val root = vbox {

        label("Select the Serial Port:")

        combobox(controller.portSelected, controller.ports)

        button("Select") { action { replaceWith<TorreyaMainView>() } }
    }

}