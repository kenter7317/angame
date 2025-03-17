package per.kenter7317.shop

import lombok.Getter

class ShopData(
    @Getter
    internal val stockList: List<Stock>
)
