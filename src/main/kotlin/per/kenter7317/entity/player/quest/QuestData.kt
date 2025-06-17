package per.kenter7317.entity.player.quest

import per.kenter7317.entity.player.quest.require.QuestsRequirement
import per.kenter7317.entity.player.quest.reward.QuestsReward

class QuestData(
    internal val questId: String,
    internal var questLawTexts: List<String>,
    internal var questDescription: String,
    internal var questRewards: List<QuestsReward>,
    internal var questRequirements: List<QuestsRequirement>
)
{
}
