package per.kenter7317.entity

import java.util.UUID

abstract class Entity {

    protected lateinit var attributes: HashMap<EntityAttribute, Any?>

    protected lateinit var possibleAttributes: HashMap<EntityAttribute, Class<*>>

     lateinit var uuid: UUID

    protected fun updateAttributes(attributes: Map<EntityAttribute, Any>) {
        attributes.forEach { (attribute, value) ->
            if (hasAttribute(attribute) && isValidAttribute(attribute, value) && canHasAttribute(attribute)) {
                this.attributes[attribute] = value
            }
        }
    }

    fun getAttribute(attribute: EntityAttribute): Any {
        return attributes[attribute] ?: throw IllegalArgumentException("Attribute not found")
    }

    private fun hasAttribute(attribute: EntityAttribute): Boolean {
        return attributes.containsKey(attribute)
    }

    private fun isValidAttribute(attribute: EntityAttribute, value: Any): Boolean {
        return possibleAttributes.containsKey(attribute) && possibleAttributes[attribute] == value.javaClass
    }

    private fun canHasAttribute(attribute: EntityAttribute): Boolean {
        return possibleAttributes.contains(attribute)
    }
}