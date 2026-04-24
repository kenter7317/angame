package per.kenter7317.per.kenter7317.extension

import per.kenter7317.extension.RunnableString
import per.kenter7317.extension.circleSelection
import java.util.function.IntConsumer

interface SelectableItems {

    var currentSelection : Int
    var items: List<RunnableString>
    var selectionChangeConsumers: List<IntConsumer>?

    fun moveSelection(movePrevious: Boolean) {
        val size = this.items.size
        this.currentSelection = circleSelection(this.currentSelection, size, movePrevious)
    }

    fun changeSelection(last: Int, current: Int)
}
