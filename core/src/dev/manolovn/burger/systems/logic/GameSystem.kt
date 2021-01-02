package dev.manolovn.burger.systems.logic

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.AsteroidsGame
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
            title("YOU'RE DEAD")
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
            draw(batch, s, 300f, 250f)
        }
    }

    private fun subtitle(s: String) {
        with(assetSystem.fontSmall) {
            draw(batch, s, 295f, 190f)
        }
    }

    override fun end() {
        batch.end()
    }
}
