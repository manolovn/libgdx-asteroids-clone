package dev.manolovn.burger.systems.logic

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.systems.entity.SpawnerSystem.Group.ASTEROID
import dev.manolovn.burger.systems.entity.SpawnerSystem.Group.PLAYER
import dev.manolovn.burger.systems.render.AssetsSystem

@All
class GameSystem(
    private val game: BurgerMenuGame,
    private val batch: SpriteBatch,
) : EntitySystem() {

    private lateinit var assetSystem: AssetsSystem
    private lateinit var groupManager: GroupManager

    override fun begin() {
        batch.begin()
    }

    override fun processSystem() {
        if (groupManager.getEntities(ASTEROID).isEmpty) {
            with(assetSystem.font) {
                draw(batch, "YOU WIN!!", 300f, 250f)
            }
            with(assetSystem.fontSmall) {
                draw(batch, "press SPACE to restart", 200f, 150f)
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                // restart game
                game.restart()
                return
            }
        }
        if (groupManager.getEntities(PLAYER).isEmpty) {
            with(assetSystem.font) {
                draw(batch, "YOU'RE DEAD", 300f, 250f)
            }
            with(assetSystem.fontSmall) {
                draw(batch, "press SPACE to restart", 200f, 150f)
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                // restart game
                game.restart()
                return
            }
        }
    }

    override fun end() {
        batch.end()
    }
}
