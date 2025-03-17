package per.kenter7317.shop

import per.kenter7317.gui.shop.ShopGui

class Shop {
    class Builder {
        private var stockList: List<Stock> = emptyList()
        fun stockList(stockList: List<Stock>) = apply { this.stockList = stockList }
        fun build() = ShopData(stockList)
    }
    fun construct(): ShopGui {
        return ShopGui()
    }
}