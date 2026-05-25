package per.kenter7317.data.entity
import java.util.HashMap
import java.util.UUID
import de.gurkenlabs.litiengine.entities.Entity

abstract class AEntity : Entity() {
	/** any attributes */
	typealias attr_t		= HashMap<EntityAttribute, Any?>
	/** valid attributes */
	typealias attr_val_t		= HashMap<EntityAttribute, Class<*>>

	protected lateinit var attributes		: attr_t
	protected lateinit var possibleAttributes	: attr_val_t

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
