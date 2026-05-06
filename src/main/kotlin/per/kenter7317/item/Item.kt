package per.kenter7317.item

import lombok.Getter
import per.kenter7317.extension.util.RunnableString

class Item(
    private val itemData: ItemData,
    @Getter
    private val coefficientList: Map<String, Int>, // this is for item effect. ex) powerUp 10 second
    @Getter
    private val itemMethod: () -> Unit,
    @Getter
    private val defaultPrice: UInt,
) : RunnableString(itemMethod, itemData.itemName)