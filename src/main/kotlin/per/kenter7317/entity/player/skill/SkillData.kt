package per.kenter7317.entity.player.skill

import lombok.Getter

class SkillData(
    @Getter
    val skillId: String,
    internal var skillLevel: Int,
    @Getter internal var skillExperience: Int
) {
    enum class SkillType {
        PSYCHICS, MAGIC, CUSTOM, HOLY
    }

    inner class Builder {
        private var skillId: String = "N/A"
        private var skillType: SkillType = SkillType.CUSTOM
        private var skillLevel: Int = 0
        private var skillExperience: Int = 0
        fun setSkillType(skillType: SkillType): Builder {
            this.skillType = skillType
            return this
        }

        fun build(): SkillData {
            return SkillData(skillId, skillLevel, skillExperience)
        }
    }

}
