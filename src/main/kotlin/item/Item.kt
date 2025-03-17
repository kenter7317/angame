package per.kenter7317.item

import lombok.Getter
import per.kenter7317.entity.player.skill.SkillData
import per.kenter7317.entity.player.skill.SkillLoader

class Item(
    private val itemData: ItemData,
    @Getter
    private val coefficientList: Map<String, Int>,
    @Getter
    private val itemMethod : () -> Unit,
    @Getter
    private val defaultPrice: Int,
    ) {

}