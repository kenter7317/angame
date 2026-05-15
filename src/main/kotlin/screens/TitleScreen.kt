package per.kenter7317.screens

import de.gurkenlabs.litiengine.Align
import de.gurkenlabs.litiengine.Game
import de.gurkenlabs.litiengine.IUpdateable
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
import per.kenter7317.extension.data.FontStyle
import per.kenter7317.extension.util.RunnableString
import per.kenter7317.extension.util.setResourceFont
import per.kenter7317.gui.shop.Enum.AlignMethod
import java.awt.Color
import java.awt.Graphics2D
import java.awt.event.KeyEvent

@Suppress("UNUSED_PARAMETER")
class TitleScreen : GameScreen("Title"), IUpdateable {

    class Builder {
        private lateinit var menu: ControllableMenu
        private lateinit var backgroundComponent: GuiComponent
        private lateinit var logoComponent: GuiComponent
        private lateinit var fontStyle: FontStyle
        private lateinit var align: AlignMethod

        fun setMenu(menu: ControllableMenu) = apply { this.menu = menu }
        fun setBackgroundComponent(backgroundComponent: GuiComponent) =
            apply { this.backgroundComponent = backgroundComponent }

        fun setLogoComponent(logoComponent: GuiComponent) = apply { this.logoComponent = logoComponent }
        fun setFontStyle(fontStyle: FontStyle) = apply { this.fontStyle = fontStyle }
        fun setAlign(align: AlignMethod) = apply { this.align = align }

        fun injectDependency(it : TitleScreen) = apply {
            if (this.takeIf(
                    {this::menu.isInitialized && this::backgroundComponent.isInitialized && this::logoComponent.isInitialized && this::fontStyle.isInitialized && this::align.isInitialized
                    }
                ) == null
            ) {
                throw IllegalStateException(" [AnGame] ${this.javaClass.name} : All dependencies must be initialized before building the TitleScreen.")
            }
            it.menu = this.menu
            it.backgroundComponent = this.backgroundComponent
            it.logoComponent = this.logoComponent
            it.fontStyle = this.fontStyle
        }

        fun build(): TitleScreen {
            val titleScreen = TitleScreen()
            injectDependency(titleScreen)
            return titleScreen
        }

        fun buildWithDefault(): TitleScreen {
            Game.log().warning("Using default values for TitleScreen dependencies. Consider using the builder methods to set custom values.")
            val titleScreen = TitleScreen()
            titleScreen.menu = this.menu
            titleScreen.backgroundComponent = ImageComponent(0.0, 0.0, Resources.images().get("menu-bg.png"))
            titleScreen.logoComponent = ImageComponent(0.0, 0.0, Resources.images().get("menu-bg.png"))
            titleScreen.fontStyle = this.fontStyle
            return titleScreen
        }
    }

    private lateinit var menu: ControllableMenu
    private lateinit var backgroundComponent: GuiComponent
    private lateinit var logoComponent: GuiComponent
    private lateinit var fontStyle: FontStyle
    private lateinit var align: AlignMethod

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
            comp.setResourceFont(fontStyle.font, fontStyle.size)
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
        Resources.spritesheets().loadFrom("sprites.info")
        this.menu = ControllableMenu(
            Game.window().center.x - 400,
            Game.window().center.y - 100,
            800.0,
            400.0,
            null,
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
        components.add(loadLogo())
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
        compositionFadeOut(this.menu)
    }

    private fun hideTitleBackground() {

    }

    private fun hideTitleForeground() {
        this.components.forEach { compositionFadeOut(it) }
    }

    private fun compositionFadeOut(comp: GuiComponent) {
        comp.setTweenValues(TweenType.OPACITY, floatArrayOf(1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0.0f))
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

    private fun loadLogo(): ImageComponent {
        val logo = ImageComponent(Game.window().center.x, 0.0, Resources.images().get("menu-logo.png"))
        logo.imageAlign = Align.CENTER
        return logo
    }
}
