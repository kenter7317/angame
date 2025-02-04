package per.kenter7317.gui

import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.gui.ComponentMouseEvent
import de.gurkenlabs.litiengine.gui.ImageComponent
import de.gurkenlabs.litiengine.gui.ImageComponentList
import per.kenter7317.extension.RunnableString
import per.kenter7317.extension.circleSelection
import java.util.function.IntConsumer
import kotlin.properties.Delegates

class ControllableMenu(
    x: Double,
    y: Double,
    width: Double,
    height: Double,
    background: Spritesheet?,
    vararg items: RunnableString
) : ImageComponentList(x, y, width, height, items.size, 1, null, background) {

    private var currentSelection by Delegates.notNull<Int>()
    private var items: Array<RunnableString>
    private var selectionChangeConsumers: List<IntConsumer>?

    init {
        this.currentSelection = 0
        this.items = items.asIterable().toList().toTypedArray()
        this.selectionChangeConsumers = List<IntConsumer>(items.size) { i ->
            IntConsumer {
                fun accept(value: Int) {
                    this.cellComponents[i].isHovered = i == value
                }
            }
        }
    }

    override fun prepare() {
        super.prepare()
        for (i in items.indices) {
            val menuButton = this.cellComponents[i] as ImageComponent
            menuButton.text = items[i].toString()
            menuButton.onHovered {
                changeSelection(this.currentSelection, i)
                this.currentSelection = this.cellComponents.indexOf(menuButton)
            }
            menuButton.onClicked { _: ComponentMouseEvent? ->
                changeSelection(this.currentSelection, i)
                this.runCurrentSelection()
            }

        }
    }

    fun runCurrentSelection() {
        items[this.currentSelection].run()
    }

    fun moveSelection(minus: Boolean) {
        val size = this.cellComponents.size
        this.currentSelection = circleSelection(this.currentSelection, size, minus)
        this.hoverSelection(circleSelection(this.currentSelection, this.cellComponents.size, !minus))
    }

    private fun hoverSelection(lastLocation: Int) {
        changeSelection(lastLocation, this.currentSelection)
    }

    private fun changeSelection(last: Int, current: Int) {
        this.cellComponents[last].isHovered = false
        this.cellComponents[current].isHovered = true
    }

}