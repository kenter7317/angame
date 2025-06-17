package per.kenter7317.entity.player.quest

import per.kenter7317.entity.player.quest.require.QuestsRequirement
import per.kenter7317.entity.player.quest.require.QuestsRequirementType
import kotlin.collections.forEach

class QuestManager {
    private val quests: MutableList<QuestData> = mutableListOf()

    fun addQuest(quest: QuestData) {
        quests.add(quest)
    }

    fun removeQuest(quest: QuestData) {
        quests.remove(quest)
    }

    fun getQuests(): List<QuestData> {
        return quests
    }

    private fun getQuest(questId: String): QuestData? {
        return quests.find { it.questId == questId }
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