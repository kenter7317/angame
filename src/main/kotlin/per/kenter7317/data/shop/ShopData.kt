package per.kenter7317.data.shop

import lombok.Getter

class ShopData(
    @Getter
    internal val stockList: List<Stock>,

    internal val reference: Any?,

    internal val shopID: Long
)
