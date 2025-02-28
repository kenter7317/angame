package per.kenter7317.entity.player

import de.gurkenlabs.litiengine.Direction
import per.kenter7317.entity.AEntity
import per.kenter7317.entity.EntityAttribute

class Player : AEntity() {
    init {
        attributes = hashMapOf(
            EntityAttribute.Health to 100,
            EntityAttribute.MaxHealth to 100,
            EntityAttribute.Attack to 0,
            EntityAttribute.Defense to 20,
            EntityAttribute.Speed to 5,
            EntityAttribute.Level to 1,
            EntityAttribute.Experience to 0,
            EntityAttribute.Inventory to mutableListOf(null),
            EntityAttribute.Equipment to mutableListOf(null),
            EntityAttribute.Position to Pair(0, 0),
            EntityAttribute.Direction to Direction.DOWN,
            EntityAttribute.Image to null,
            EntityAttribute.Name to "치즈루",
            EntityAttribute.Description to "67Kg66W07LK4IOy2nOyLoOydmCDrgpjsnbQg67aI66qFIOyXrOyekOyVhOydtOuhnCDsobTsnqztlaAg7JiI7KCV7J207JiA7Jy864KYLCAK7J20IO2DgOyehOudvOyduOyXkCDtjrjsnoXrkJwg7KG07J6sLiAK"
        )
        possibleAttributes = hashMapOf(
            EntityAttribute.Health to Int::class.java,
            EntityAttribute.MaxHealth to Int::class.java,
            EntityAttribute.Attack to Int::class.java,
            EntityAttribute.Defense to Int::class.java,
            EntityAttribute.Speed to Int::class.java,
            EntityAttribute.Level to Int::class.java,
            EntityAttribute.Experience to Int::class.java,
            EntityAttribute.Inventory to List::class.java,
            EntityAttribute.Equipment to List::class.java,
            EntityAttribute.Position to Pair::class.java,
            EntityAttribute.Direction to Direction::class.java,
            EntityAttribute.Image to Any::class.java,
            EntityAttribute.Name to String::class.java,
            EntityAttribute.Description to String::class.java,
            EntityAttribute.Magic to Any::class.java
        )
    }

    fun getPlayerData() : PlayerData {
        return PlayerData(
            getLevel(),
            getExperience(),
            getHealth(),
            getMaxHealth(),
            getAttack(),
            getDefense(),
            getSpeed(),
            getInventory(),
            getEquipment(),
            getPosition(),
            getDirection(),
            getImage(),
            getName(),
            getDescription(),
            getMagic()
        )

    }

    fun levelUp() {
        val level = getLevel()
        updateAttributes(
            mapOf(
                EntityAttribute.Level to level + 1)
        )
    }
    private fun getHealth(): Int {
        return getAttribute(EntityAttribute.Health) as Int
    }

    private fun getMaxHealth(): Int {
        return getAttribute(EntityAttribute.MaxHealth) as Int
    }

    private fun getAttack(): Int {
        return getAttribute(EntityAttribute.Attack) as Int
    }

    private fun getDefense(): Int {
        return getAttribute(EntityAttribute.Defense) as Int
    }

    private fun getSpeed(): Int {
        return getAttribute(EntityAttribute.Speed) as Int
    }
    // <summary> Returns the player's level </summary>
    private fun getLevel(): Int {
        return getAttribute(EntityAttribute.Level) as Int
    }
    // <summary> Returns the player's experience </summary>
    private fun getExperience(): Int {
        return getAttribute(EntityAttribute.Experience) as Int
    }
    // <summary> Returns the player's inventory </summary>
    private fun getInventory(): List<Any?> {
        return getAttribute(EntityAttribute.Inventory) as List<Any?>
    }

    private fun getEquipment(): List<Any?> {
        return getAttribute(EntityAttribute.Equipment) as List<Any?>
    }

    private fun getPosition(): Pair<Int, Int> {
        return getAttribute(EntityAttribute.Position) as Pair<Int, Int>
    }

    private fun getDirection(): Direction {
        return getAttribute(EntityAttribute.Direction) as Direction
    }

    private fun getImage(): Any? {
        return getAttribute(EntityAttribute.Image)
    }

    override fun getName(): String {
        return getAttribute(EntityAttribute.Name) as String
    }

    private fun getDescription(): String {
        return getAttribute(EntityAttribute.Description) as String
    }

    private fun getMagic(): Any? {
        return getAttribute(EntityAttribute.Magic)
    }
}