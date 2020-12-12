package dev.manolovn.burger

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.screen.InGameScreen
import dev.manolovn.burger.util.Assets

class BurgerMenuGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var assets: Assets

    override fun create() {
        batch = SpriteBatch()
        assets = Assets().loadAll()

        // initial screen
        setScreen(InGameScreen(this))
    }

    override fun dispose() {
        getScreen().dispose()
        
        batch.dispose()
        assets.dispose()
    }
}
