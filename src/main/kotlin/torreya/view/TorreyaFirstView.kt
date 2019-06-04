package torreya.view

import tornadofx.*
import torreya.app.controller

class TorreyaFirstView : View("Torreya") {

    override val root = vbox {

        style { padding = box(300.px, 175.px) }

        label("Select the Serial Port:")

        combobox(controller.portSelected, controller.ports)

        button("Select") { action { replaceWith<TorreyaMainView>() } }
    }

}