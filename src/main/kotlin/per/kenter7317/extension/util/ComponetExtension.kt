package per.kenter7317.extension.util

import de.gurkenlabs.litiengine.gui.ImageComponent
import de.gurkenlabs.litiengine.resources.Resources
import per.kenter7317.extension.data.FontStyle

fun ImageComponent.setResourceFont(fontStyle: FontStyle) {
    this.font = Resources.fonts().get(fontStyle.font).deriveFont(fontStyle.size)
}