package dev.manolovn.burger.systems

import com.artemis.annotations.Wire
import com.artemis.utils.EntityBuilder
import com.badlogic.gdx.math.MathUtils
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.BurgerMenuGame.Companion.H
import dev.manolovn.burger.BurgerMenuGame.Companion.W
import dev.manolovn.burger.components.*
import net.mostlyoriginal.api.system.core.PassiveSystem
import kotlin.random.Random

@Wire
class SpawnerSystem(private val game: BurgerMenuGame) : PassiveSystem() {

    override fun initialize() {
        super.initialize()

        initBackground()
        initSpaceship()
        //initGems()
    }

    private fun initBackground() {
        EntityBuilder(world)
            .with(
                Pos(-W/2f, -H/2f),
                Angle(0f),
                Scale(),
                Renderable(1),
                Sprite(game.assets.bg),
                Color(game.assets.bg.color)
            )
            .build()
    }

    private fun initSpaceship() {
        EntityBuilder(world)
            .with(
                Ship(),
                Pos(),
                Angle(MathUtils.PI),
                Scale(),
                Renderable(2),
                Control(),
                Sprite(game.assets.spaceship),
                Color(game.assets.bg.color)
            )
            .build()
    }

    private fun initGems() {
        Array(Board.COLS) { i ->
            Array(Board.ROWS) { j ->
                val kind = Random.nextInt(game.assets.gems.size)
                buildGemEntity(i, j, kind)
            }
        }
    }

    private fun buildGemEntity(i: Int, j: Int, kind: Int) {
        EntityBuilder(world)
            .with(
                Pos(Board.CELL_SIZE.toFloat() * i + Board.X_OFFSET, Board.CELL_SIZE.toFloat() * j + Board.Y_OFFSET),
                Renderable(2),
                Matcheable(i, j, kind, 0),
                Sprite(game.assets.gems[kind]),
                Color(game.assets.gems[kind].color)
            )
            .build()
    }
}