package per.kenter7317.entity.player

data class PlayerData(val level: Int, val experience: Int, val health: Int, val maxHealth: Int, val attack: Int, val defense: Int, val speed: Int, val inventory: List<Any?>, val equipment: List<Any?>, val position: Pair<Int, Int>, val direction: Any, val image: Any?, val name: String, val description: String, val magic: Any?)
