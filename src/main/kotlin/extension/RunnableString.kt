package per.kenter7317.extension

import java.lang.reflect.Method

class RunnableString(val action: () -> Unit, private val string: String) {

    fun run() {
        action()
    }

    override fun toString(): String {
        return string
    }
}