package per.kenter7317.extension

import java.awt.image.BufferedImage
import java.awt.image.ColorModel
import java.awt.image.WritableRaster
import java.util.*

class HoldingImage<T>(
    cm: ColorModel?,
    raster: WritableRaster?,
    isRasterPremultiplied: Boolean,
    properties: Hashtable<*, *>?,
    private var holding: T
) : BufferedImage(cm, raster, isRasterPremultiplied, properties) {
    fun getHolding(): T? {
        return holding
    }
}
