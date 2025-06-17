package per.kenter7317.entity.player.skill

import lombok.Getter

class Skill(
    private val skillData: SkillData,
    private val skillLoader: SkillLoader,
    @Getter
    private val coefficientList: Map<String, Int>,
    @Getter
    private val skillMethod : () -> Unit
) {
    fun getSkillLevel(): Int {
        return skillData.skillLevel
    }

    fun getSkillExperience(): Int {
        return skillData.skillExperience
    }

    fun getSkillId(): String {
        return skillData.skillId
    }

    fun runSkill() {
        skillMethod()
    }
}