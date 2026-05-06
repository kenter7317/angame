package per.kenter7317.data.entity.player.quest.reward

class QuestsReward(private val rewardType: QuestsRewardType, private val rewardAmount: Int) {
    fun getRewardType(): QuestsRewardType {
        return rewardType
    }
    fun getRewardAmount(): Int {
        return rewardAmount
    }
}
