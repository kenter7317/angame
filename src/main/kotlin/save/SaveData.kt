package per.kenter7317.save

import de.gurkenlabs.litiengine.entities.Entity
import per.kenter7317.entity.player.PlayerData
import per.kenter7317.event.EventData
import per.kenter7317.entity.player.quest.QuestData
import per.kenter7317.entity.player.skill.SkillData
import per.kenter7317.item.ItemData
import per.kenter7317.npcaa.NpcData
import per.kenter7317.shop.ShopData
import java.io.File

class SaveData(val file : File) {

    lateinit var playerData: PlayerData
    lateinit var mapData: List<Entity>
    lateinit var npcData: List<NpcData>
    lateinit var eventData: List<EventData>
    lateinit var questData: List<QuestData>
    lateinit var shopData: List<ShopData>
    lateinit var itemData: List<ItemData>
    lateinit var skillData: List<SkillData>

    fun setData(T: Any): SaveData {
        when(T) {
            is PlayerData -> playerData = T
            is List<*> -> {
                when(T[0]) {
                    is Entity -> mapData = T as List<Entity>
                    is NpcData -> npcData = T as List<NpcData>
                    is EventData -> eventData = T as List<EventData>
                    is QuestData -> questData = T as List<QuestData>
                    is ShopData -> shopData = T as List<ShopData>
                    is ItemData -> itemData = T as List<ItemData>
                    is SkillData -> skillData = T as List<SkillData>
                }
            }
        }
        return this
    }
}
