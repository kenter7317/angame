package per.kenter7317.gui

import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.gui.ImageComponentList
import per.kenter7317.extension.SelectableItems
import per.kenter7317.extension.util.RunnableString
import java.awt.Image
import java.util.function.IntConsumer

class SaveDataList(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    rows: Int,
    columns: Int,
    images: MutableList<Image>?,
    background: Spritesheet?
) : SelectableItems, ImageComponentList(x, y, width, height, rows, columns, images, background) {

    override var currentSelection: Int = 0
    override var items: List<RunnableString> = emptyList()
    override var selectionChangeConsumers: List<IntConsumer>? = null
    override fun changeSelection(last: Int, current: Int) {

    }

}