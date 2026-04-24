package per.kenter7317.extension

import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.gui.ComponentMouseEvent
import de.gurkenlabs.litiengine.gui.ImageComponent
import de.gurkenlabs.litiengine.gui.ImageComponentList
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
    private var cachedSelection = 0 // Implement with engine

    init {
        this.currentSelection = cachedSelection
        this.items = items.toList().toTypedArray()
        this.selectionChangeConsumers = List(items.size) { i ->
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
    fun moveSelection(movePrevious: Boolean) {
        val size = this.cellComponents.size
        this.currentSelection = circleSelection(this.currentSelection, size, movePrevious)
        this.hoverSelection(circleSelection(this.currentSelection, this.cellComponents.size, !movePrevious))
    }

    private fun hoverSelection(lastLocation: Int) {
        changeSelection(lastLocation, this.currentSelection)
    }

    private fun changeSelection(last: Int, current: Int) {
        this.cellComponents[last].isHovered = false
        this.cellComponents[current].isHovered = true
    }
}