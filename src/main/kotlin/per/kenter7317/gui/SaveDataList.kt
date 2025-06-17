package per.kenter7317.gui

import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.gui.ImageComponentList
import java.awt.Image

class SaveDataList(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    rows: Int,
    columns: Int,
    images: MutableList<Image>?,
    background: Spritesheet?
) : ImageComponentList(x, y, width, height, rows, columns, images, background) {

}