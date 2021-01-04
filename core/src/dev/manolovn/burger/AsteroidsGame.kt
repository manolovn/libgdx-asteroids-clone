package dev.manolovn.burger

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.screen.InGameScreen
import dev.manolovn.burger.screen.MenuScreen

class AsteroidsGame : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()

        setScreen(MenuScreen(this))
    }

    fun restart() {
        setScreen(InGameScreen(this))
    }

    override fun dispose() {
        getScreen().dispose()
        
        batch.dispose()
    }

    companion object {
        const val W = 800f
        const val H = 480f
    }
}
