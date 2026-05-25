package per.kenter7317.gui.shop

import de.gurkenlabs.litiengine.graphics.Spritesheet
import per.kenter7317.data.shop.Shop
import java.awt.Graphics2D
import kotlin.properties.Delegates

class DefaultShopGUI(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    background: Spritesheet,
    phaseList: List<ShopGuiPhase>
) : ShopGui(x, y, width, height, background, phaseList) {

    var phase : ShopGuiPhase by Delegates.notNull()
    var shop : Shop by Delegates.notNull<Shop>()

    override fun render(g: Graphics2D?) {
        super.render(g)
    }

    fun changePhase(phase: ShopGuiPhase) {
        this.phase = phase
    }

    fun currentPhaseInt(): Int {
        return this.phase.phaseInt
    }

}