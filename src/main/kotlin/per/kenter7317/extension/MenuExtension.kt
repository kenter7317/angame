package per.kenter7317.extension

import de.gurkenlabs.litiengine.gui.Menu

private fun Boolean.toInt() = if (this) 1 else 0

fun circleSelection(current: Int, size: Int, minus: Boolean): Int {
    return (current + size + ((minus.toInt() * -2) + 1)) % size
}
private fun Menu.moveSingleSelection(minus: Boolean) {
    val size = this.cellComponents.size
    this.currentSelection = circleSelection(this.currentSelection, size, minus)
    this.hoverSelection(minus)
}
fun Menu.plusSelection() {
    this.moveSingleSelection(false)
}

fun Menu.minusSelection() {
    this.moveSingleSelection(true)
}

private fun Menu.hoverSelection(minus: Boolean) { // depending on Extension Method moveSingleSelection(Boolean) (MenuExtension.kt:21)
    this.cellComponents[circleSelection(this.currentSelection, this.cellComponents.size, !minus)].isHovered = false
    this.cellComponents[this.currentSelection].isHovered = true
}
