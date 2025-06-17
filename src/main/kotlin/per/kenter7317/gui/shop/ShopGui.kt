package per.kenter7317.gui.shop

import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.gui.GuiComponent
import per.kenter7317.AlignMethod
import kotlin.properties.Delegates

abstract class ShopGui(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    background: Spritesheet,
    private val phaseList: List<ShopGuiPhase>
) :
    GuiComponent(x, y, width, height) {
    private var currentSelection = 0
    private lateinit var phase: ShopGuiPhase
    private val alignMethod: AlignMethod by Delegates.notNull()

    class ShopGuiPhase {
        var phase: Int by Delegates.notNull()
        var phaseName: String by Delegates.notNull()
        var componentList: List<ShopComponent> by Delegates.notNull()
        val phaseType: Int by Delegates.notNull()
        var alignColumn: Int by Delegates.notNull()
    }

}

