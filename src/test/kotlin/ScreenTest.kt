package per.kenter7317

import de.gurkenlabs.litiengine.Game
import org.junit.jupiter.api.Test
import per.kenter7317.screens.TitleScreen

class ScreenTest {

    @Test
    fun testScreenInit(){
        Game.init()
        TitleScreen.Builder().injectDependency(TitleScreen.Builder().buildWithDefault())
    }
}