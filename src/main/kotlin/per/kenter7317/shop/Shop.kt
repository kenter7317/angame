package per.kenter7317.shop

import per.kenter7317.item.Item

class Shop(
    var stockList: HashMap<Item, Int>,
    var shopData: ShopData,
) {
    fun appendStock(stock: Stock) {
        stockList[stock.item] = stock.stock
    }

    fun deleteStock(stock: Stock) {
        stockList.remove(stock.item)
    }

    fun addItemStock(item: Item, stockInt: Int) {
        if (stockList.contains(item)) {
            stockList[item] = stockList[item]!! + stockInt
        }
    }

    fun deleteItemStock(item: Item, stockInt: Int) {
        if (stockList.contains(item)) {
            stockList[item] = stockList[item]!! - stockInt
        }
    }

    fun getReferences(): Any? {
        return shopData.reference
    }


    class Builder {
        private var stockList: List<Stock> = emptyList()
        private var shopData: ShopData? = null
        fun stockList(stockList: List<Stock>) = apply { this.stockList = stockList }
        fun reference(reference: Any) = apply {
            this.shopData =
                this@Builder.shopData?.let {
                    this@Builder.shopData?.let { ShopData(this.stockList, reference, it.shopID) }
                }
        }
        fun shopID(shopID: Long) = apply {
            this@Builder.shopData?.let { ShopData(this.stockList, it.reference, shopID) }
        }
        fun build() = Shop(
            stockList.associate { it.item to it.stock } as HashMap<Item, Int>,
            ShopData(stockList, null, 0)
        )
    }
//    fun construct(): ShopGui {
//        //return ShopGui()
//    }
}