package torreya.app

import javafx.collections.FXCollections
import jssc.SerialPortList
import tornadofx.*
import torreya.view.TorreyaFirstView
import torreya.view.TorreyaMainView

class TorreyaApp: App(TorreyaFirstView::class, TorreyaStyles::class) {

    init {

        reloadStylesheetsOnFocus()
    }


    fun parser(message: String) {

        kotlin.io.println("msg: $message")
    }
}
