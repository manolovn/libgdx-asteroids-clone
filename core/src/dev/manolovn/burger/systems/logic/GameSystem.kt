package dev.manolovn.burger.systems.logic

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.AsteroidsGame
import dev.manolovn.burger.AsteroidsGame.Companion.H
import dev.manolovn.burger.AsteroidsGame.Companion.W
import dev.manolovn.burger.systems.entity.Group.ASTEROID
import dev.manolovn.burger.systems.entity.Group.PLAYER
import dev.manolovn.burger.systems.render.AssetsSystem

@All
class GameSystem(
    private val game: AsteroidsGame,
    private val batch: SpriteBatch,
) : EntitySystem() {

    private lateinit var assetSystem: AssetsSystem
    private lateinit var groupManager: GroupManager

    override fun begin() {
        batch.begin()
    }

    override fun processSystem() {
        if (groupManager.getEntities(ASTEROID).isEmpty) {
            title("YOU WIN!!")
            subtitle("press SPACE to restart")
            waitRestart()
        }
        if (groupManager.getEntities(PLAYER).isEmpty) {
            title("GAME OVER")
            subtitle("press SPACE to restart")
            waitRestart()
        }
    }

    private fun waitRestart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // restart game
            game.restart()
            return
        }
    }

    private fun title(s: String) {
        with(assetSystem.font) {
            val glyphLayout = GlyphLayout(this, s)
            draw(batch, s, (W - glyphLayout.width) / 2, (H + glyphLayout.height) / 2)
        }
    }

    private fun subtitle(s: String) {
        with(assetSystem.fontSmall) {
            val glyphLayout = GlyphLayout(this, s)
            draw(batch, s, (W - glyphLayout.width) / 2, 210f)
        }
    }

    override fun end() {
        batch.end()
    }
}
