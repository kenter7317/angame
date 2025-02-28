package per.kenter7317.screens

import de.gurkenlabs.litiengine.Game
import de.gurkenlabs.litiengine.IUpdateable
import de.gurkenlabs.litiengine.graphics.ImageRenderer
import de.gurkenlabs.litiengine.graphics.TextRenderer
import de.gurkenlabs.litiengine.gui.GuiComponent
import de.gurkenlabs.litiengine.gui.ImageComponent
import de.gurkenlabs.litiengine.gui.TextFieldComponent
import de.gurkenlabs.litiengine.gui.screens.GameScreen
import de.gurkenlabs.litiengine.input.Input
import de.gurkenlabs.litiengine.resources.Resources
import de.gurkenlabs.litiengine.util.ColorHelper
import de.gurkenlabs.litiengine.util.Imaging
import per.kenter7317.extension.RunnableString
import per.kenter7317.gui.ControllableMenu
import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage
import kotlin.concurrent.timer

class TitleScreen : GameScreen("Title"), IUpdateable {

    private lateinit var BG: BufferedImage

    private lateinit var menu: ControllableMenu

    private lateinit var LOGO: BufferedImage

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
            Resources.spritesheets().get("menu-bg.png"),
            RunnableString(this::startGame, "새 이야기를 작성한다."),
            RunnableString(this::loadGame, "이야기를 불러온다."),
            RunnableString(this::setting, "설정한다.")
        )
        Input.keyboard().onKeyReleased { event: KeyEvent ->
            val menu = this.menu
            if (menu.isVisible) {
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
        }
        components.add(this.menu)
        components.add(
            ImageComponent(
                Game.window().center.x - LOGO.width / 2,
                Game.window().height * 2.5 / 8 - LOGO.height * 5 / 6,
                LOGO
            )
        )
    }

    override fun render(g: Graphics2D?) {
        ImageRenderer.render(g, Imaging.scale(BG, 1920, 1080), 0.0, 0.0)
        g!!.color = Color(26, 47, 4)

        val originalHints = g.renderingHints

        TextRenderer.enableTextAntiAliasing(g)

        g.setRenderingHints(originalHints)
        super.render(g)
    }

    override fun update() {
    }

    private fun startGame() {
        hideTitleForeground()
        loadTexts()
        Game.screens().display("GAME")
    }

    private fun loadTexts() {
        this.components.add(
           TextFieldComponent(
                Game.window().center.x - 400,
                Game.window().center.y - 100,
                800.0,
                400.0,
                "이 이야기를 적어내려가는 당신에게는 약 3000년 뒤. 다른 세계에서의 당신은 엄청난 고난을 겪고 있었다."
           )
        )
    }

    private fun hideTitleForeground() {
        this.components.forEach { comp: GuiComponent ->
            fadeOutAndDisappear(comp, 700)
        }
    }

    private fun fadeOutAndDisappear(comp: GuiComponent, duration: Int = 700) {
        val originalColor: Color = comp.appearance.foreColor // 현재 foreColor 가져오기
        var alpha = 1.0f // 초기 투명도 (완전 불투명)

        val interval = 50 // 50ms마다 실행
        val steps = duration / interval // 전체 단계 수

        timer(period = interval.toLong(), initialDelay = 0) {
            alpha -= 1.0f / steps // 점진적으로 투명해짐

            if (alpha <= 0) {
                alpha = 0f
                comp.isVisible = false // 완전히 투명해지면 숨김
                cancel()
            }

            // 현재 색상의 투명도 조절 (A값 변경)
            val newColor = Color(originalColor.red, originalColor.green, originalColor.blue, (alpha * 255).toInt())
            comp.appearance.foreColor = newColor
        }
    }

    private fun loadGame() {
        Game.window().renderComponent.fadeOut(700)
        Game.screens().display("SaveData")
    }
    private fun setting() {
        TODO("Not yet implemented")
    }
}





