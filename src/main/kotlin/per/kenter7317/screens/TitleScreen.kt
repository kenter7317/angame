package per.kenter7317.screens

import de.gurkenlabs.litiengine.Game
import de.gurkenlabs.litiengine.IUpdateable
import de.gurkenlabs.litiengine.graphics.Spritesheet
import de.gurkenlabs.litiengine.graphics.TextRenderer
import de.gurkenlabs.litiengine.gui.GuiComponent
import de.gurkenlabs.litiengine.gui.ImageComponent
import de.gurkenlabs.litiengine.gui.screens.GameScreen
import de.gurkenlabs.litiengine.input.Input
import de.gurkenlabs.litiengine.resources.Resources
import de.gurkenlabs.litiengine.tweening.Tween
import de.gurkenlabs.litiengine.tweening.TweenType
import de.gurkenlabs.litiengine.util.ColorHelper
import per.kenter7317.extension.ControllableMenu
import per.kenter7317.extension.util.RunnableString
import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage
@Suppress("UNUSED_PARAMETER")
class TitleScreen : GameScreen("Title"), IUpdateable {

    private lateinit var BG: Spritesheet

    private lateinit var LOGO: BufferedImage

    private lateinit var menu: ControllableMenu

    private var compFontSize: Float = 48f

    override fun prepare() {
        super.prepare()
        Game.loop().attach(this)
        Game.window().renderComponent.background = Color.BLACK
        Game.graphics().baseRenderScale = 6f * Game.window().resolutionScale
        composeMenu(this.menu)
        this.menu.isEnabled = true
        this.menu.cellComponents[0].isHovered = true
    }

    private fun composeMenu(menu: ControllableMenu) {
        for (comp in menu.cellComponents) {
            comp.font = Resources.fonts().get("Sam3KRFont.ttf").deriveFont(compFontSize)
            composeSheet(
                comp, ColorHelper.decode("#255655"),
                ColorHelper.decode("#593D35")
            )
        }
    }

    private fun composeSheet(comp: ImageComponent, forecolor: Color?, hoveredForeColor: Color) {
        val sheet = Resources.spritesheets().loadFrom("sprites.info")[1]
        comp.setSpritesheet(sheet)
        comp.setTextAntialiasing(true)
        comp.appearance.foreColor = forecolor
        comp.appearanceHovered.foreColor = hoveredForeColor
    }

    override fun initializeComponents() {
        super.initializeComponents()
        BG = Resources.spritesheets().get("menu-bg.png")
        LOGO = Resources.images().get("menu-logo.png")
        this.menu = ControllableMenu(
            Game.window().center.x - 400,
            Game.window().center.y - 100,
            800.0,
            400.0,
            BG,
            RunnableString(this::startGame, "새 이야기를 작성한다."),
            RunnableString(this::loadGame, "이야기를 불러온다."),
            RunnableString(this::setting, "설정한다.")
        )
        Input.keyboard().onKeyReleased { event: KeyEvent ->
            val menu = this.menu
            when (event.keyCode) {
                KeyEvent.VK_UP -> {
                    menu.moveSelection(true)                  //  current = (size - 1) - abs((last + 1) % size)
                }

                KeyEvent.VK_DOWN -> {
                    menu.moveSelection(false)                 //    current = abs((last + 1) % size)
                }

                KeyEvent.VK_ENTER -> {
                    menu.runCurrentSelection()
                }
            }
        }
        components.add(this.menu)
        components.add(
            ImageComponent(
                Game.window().center.x - LOGO.width / 2,
                Game.window().height * 2.5 / 8 - LOGO.height * 5 / 6, LOGO
            )
        )
    }

    override fun render(graphics2D: Graphics2D?) {
        // ImageRenderer.render(graphics2D, Imaging.scale(BG, 1920, 1080), 0.0, 0.0)
        graphics2D!!.color = Color(26, 47, 4)

        val originalHints = graphics2D.renderingHints

        TextRenderer.enableTextAntiAliasing(graphics2D)

        graphics2D.setRenderingHints(originalHints)
        super.render(graphics2D)
    }

    override fun update() {
    }

    private fun startGame() {
        hideTitleForeground()
        hideTitleMenu()
        hideTitleBackground()
    }

    private fun hideTitleMenu() {
            compFadeOut(this.menu)
    }

    private fun hideTitleBackground() {

    }

    private fun hideTitleForeground() {
        this.components.forEach { compFadeOut(it) }
    }

    private fun compFadeOut(comp: GuiComponent) {
        comp.setTweenValues(TweenType.OPACITY, floatArrayOf(1.0f,0.8f,0.6f,0.4f,0.2f,0.0f))
        Tween(comp, TweenType.OPACITY, 18000).begin()

//        timer(period = interval.toLong(), initialDelay = 0) {
//            initialAlpha -= 1.0f / steps // 점진적으로 투명해짐
//
//            if (initialAlpha <= 0) {
//                initialAlpha = 0f
//                comp.isVisible = false // 완전히 투명해지면 숨김
//                cancel()
//            }
//
//            // 현재 색상의 투명도 조절 (A값 변경)
//            val newColor =
//                Color(currentForeColor.red,
//                    currentForeColor.green,
//                    currentForeColor.blue,
//                    (initialAlpha * 255).toInt())
//            comp.appearance.foreColor = newColor
    }

    private fun loadGame() {
        TODO("Not yet implemented")
    }

    private fun setting() {
        TODO("Not yet implemented")
    }

}
