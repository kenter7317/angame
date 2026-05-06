package per.kenter7317.extension.util

open class RunnableString(val action: () -> Unit, private val string: String) {

    fun run() {
        action()
    }
    override fun toString(): String {
        return string
    }
}