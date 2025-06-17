package per.kenter7317.item

import lombok.Getter

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