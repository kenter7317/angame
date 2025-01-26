package per.kenter7317.screens

import de.gurkenlabs.litiengine.Game
import de.gurkenlabs.litiengine.IUpdateable
import de.gurkenlabs.litiengine.graphics.ImageRenderer
import de.gurkenlabs.litiengine.graphics.TextRenderer
import de.gurkenlabs.litiengine.gui.Menu
import de.gurkenlabs.litiengine.gui.screens.GameScreen
import de.gurkenlabs.litiengine.input.Input
import de.gurkenlabs.litiengine.resources.Resources
import de.gurkenlabs.litiengine.util.ColorHelper
import de.gurkenlabs.litiengine.util.Imaging
import per.kenter7317.extension.*
import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage

class TitleScreen : GameScreen("Title"), IUpdateable {

    private lateinit var BG: BufferedImage

    private lateinit var menu: ControllableMenu

    private lateinit var LOGO: BufferedImage

    private val centerX = Game.window().resolution.getWidth() / 2.0

    private val centerY = Game.window().resolution.getHeight() * 1 / 2
    override fun prepare() {
        super.prepare()
        Game.loop().attach(this)
        Game.window().renderComponent.background = Color.BLACK
        Game.graphics().baseRenderScale = 6f * Game.window().resolutionScale
        for (comp in this.menu.cellComponents) {
            comp.font = Resources.fonts().get("Sam3KRFont.ttf").deriveFont(48f)
            val sheet = Resources.spritesheets().loadFrom("sprites.info")[1]
            comp.setSpritesheet(sheet)
            comp.setTextAntialiasing(true)
            comp.appearance.foreColor = ColorHelper.decode("#255655")
            comp.appearanceHovered.foreColor = ColorHelper.decode("#593D35")
        }

        this.menu.isEnabled = true
        this.menu.cellComponents[0].isHovered = true
    }

    override fun initializeComponents() {
        super.initializeComponents()
        BG = Resources.images().get("menu-bg.png")
        LOGO = Resources.images().get("menu-logo.png")
        this.menu = ControllableMenu(
            Game.window().center.x - 400,
            Game.window().center.y - 100,
            800.0,
            400.0,
            null,
            RunnableString(this::startGame,"새 이야기를 작성한다."),
            RunnableString(this::loadGame,"이야기를 불러온다."),
            RunnableString(this::setting,"설정한다.")
        )
        Input.keyboard().onKeyReleased { event: KeyEvent ->
            val menu = this.menu
            when (event.keyCode) {
                KeyEvent.VK_UP -> {
                    menu.moveSelection(true)
                    //  current = (size - 1) - abs((last + 1) % size)
                }
                KeyEvent.VK_DOWN -> {
                    menu.moveSelection(false)
                    //    current = abs((last + 1) % size)
                }
                KeyEvent.VK_ENTER -> {
                    menu.runCurrentSelection()
                }
            }
        }
        components.add(this.menu)

    }

    override fun render(g: Graphics2D?) {
        ImageRenderer.render(g, Imaging.scale(BG, 1920, 1080), 0.0, 0.0)
        g!!.color = Color(26, 47, 4)

        val originalHints = g.renderingHints

        TextRenderer.enableTextAntiAliasing(g)

        ImageRenderer.render(
            g,
            LOGO,
            Game.window().center.x - LOGO.width / 2,
            Game.window().height * 2.5 / 8 - LOGO.height * 5 / 6
        )
        g.setRenderingHints(originalHints)
        super.render(g)
    }

    override fun update() {
    }

    private fun startGame() {
        Game.screens().display("Game")
        this.menu.isEnabled = false
        this.menu.isVisible = false
        this;

    }

    private fun loadGame() {
        TODO("Not yet implemented")
    }

    private fun setting() {
        TODO("Not yet implemented")
    }
}





