package dev.manolovn.burger.systems

import com.artemis.annotations.Wire
import com.artemis.utils.EntityBuilder
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.*
import net.mostlyoriginal.api.system.core.PassiveSystem
import kotlin.random.Random

@Wire
class BoardSpawnerSystem(private val game: BurgerMenuGame) : PassiveSystem() {

    override fun initialize() {
        super.initialize()

        initBackground()
        initGems()
    }

    private fun initBackground() {
        EntityBuilder(world)
                .with(
                        Pos(0f, 0f),
                        Renderable(1),
                        Sprite(game.assets.bg)
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
                        Sprite(game.assets.gems[kind])
                )
                .build()
    }
}