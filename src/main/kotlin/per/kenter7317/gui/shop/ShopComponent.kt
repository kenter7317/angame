package per.kenter7317.gui.shop

import lombok.Getter
import org.jetbrains.annotations.Nullable
import per.kenter7317.extension.util.RunnableString
import java.awt.image.BufferedImage

@Getter
class ShopComponent(
    val image: BufferedImage,
    val text : RunnableString,
    val type: ShopComponentType
) {

}