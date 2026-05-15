package per.kenter7317.data.entity.player.quest

import per.kenter7317.data.entity.player.quest.require.QuestsRequirement
import per.kenter7317.data.entity.player.quest.require.QuestsRequirementType
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.forEach

class QuestManager {
    private val quests: ConcurrentHashMap<QuestData ,Quest> = mutableMapOf<QuestData, Quest>() as ConcurrentHashMap<QuestData, Quest>

    fun addQuest( quest: Quest) {
        quests[quest.questData] = quest
    }

    fun removeQuest(quest: QuestData) {
        quests.remove(quest)
    }

    fun getQuests(): List<QuestData> {
        return quests.keys.toList()
    }

    private fun getQuest(questId: String): QuestData? {
        return quests.keys.find { it.questId == questId }
    }
    fun checkQuest(questId: String): Boolean {
        getQuest(questId)?.let {
            it.questRequirements.forEach { requirement ->
                if (!checkRequirements(requirement)) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkRequirements(requirement: QuestsRequirement): Boolean {
        return requirementIsCompleted(requirement.getRequirementType(), requirement.getRequirementAmount())
    }

    private fun requirementIsCompleted(requirementType: QuestsRequirementType, requirementAmount: Int): Boolean {
        TODO("Not yet implemented")
    }
}