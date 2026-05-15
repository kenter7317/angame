package per.kenter7317.data.entity.player.quest.require

class QuestsRequirement(private val requirementType: QuestsRequirementType, private val requirementAmount: Int) {

    fun getRequirementType(): QuestsRequirementType {
        return requirementType
    }

    fun getRequirementAmount(): Int {
        return requirementAmount
    }
}
