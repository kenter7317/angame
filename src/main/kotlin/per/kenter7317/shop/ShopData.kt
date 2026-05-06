package per.kenter7317.shop

import lombok.Getter
import java.lang.ref.Reference

class ShopData(
    @Getter
    internal val stockList: List<Stock>,

    internal val reference: Any?,

    internal val shopID: Long
)
