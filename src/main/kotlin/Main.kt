package per.kenter7317

import de.gurkenlabs.litiengine.Game
import de.gurkenlabs.litiengine.resources.Resources
import per.kenter7317.screens.TitleScreen

fun main() {
    Game.init()
    Game.screens().add(TitleScreen())
    Game.screens().display("Title")
    Resources.load("game.litidata")
    Game.start()
}