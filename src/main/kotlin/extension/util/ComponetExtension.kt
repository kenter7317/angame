package per.kenter7317.extension.util

import de.gurkenlabs.litiengine.gui.GuiComponent
import de.gurkenlabs.litiengine.resources.Resources


fun GuiComponent.setResourceFont(fontName: FontName, fontSize: Float) {
    this.font = Resources.fonts().get(fontName).deriveFont(fontSize)

}