package dev.manolovn.burger

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.screen.InGameScreen

class BurgerMenuGame : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()

        restart()
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
