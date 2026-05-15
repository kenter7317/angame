package per.kenter7317.extension

import per.kenter7317.extension.util.RunnableString
import per.kenter7317.extension.util.circleSelection
import java.util.function.IntConsumer

interface SelectableObjects {

    var currentSelection : Int
    var objects: List<RunnableString>
    var selectionChangeConsumers: List<IntConsumer>?

    fun moveSelection(movePrevious: Boolean) {
        val size = this.objects.size
        this.currentSelection = circleSelection(this.currentSelection, size, movePrevious)
    }

    fun changeSelection(last: Int, current: Int)
}