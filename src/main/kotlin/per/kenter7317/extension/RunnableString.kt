package per.kenter7317.extension

open class RunnableString(private val action: () -> Unit, private val string: String) {
    fun run() {
        action()
    }
    override fun toString(): String {
        return string
    }
}