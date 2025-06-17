package per.kenter7317.gui.shop

import de.gurkenlabs.litiengine.graphics.Spritesheet
import java.awt.Graphics2D

class DefaultShopGUI(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    background: Spritesheet,
    phaseList: List<ShopGuiPhase>
) : ShopGui(x, y, width, height, background, phaseList) {

    override fun render(g: Graphics2D?) {
        super.render(g)

    }


}