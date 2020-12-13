package dev.manolovn.burger.systems

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.Board
import java.util.stream.IntStream

@All
class RenderingSystem(private val game: BurgerMenuGame): EntitySystem() {

    private lateinit var board: Board

    override fun initialize() {
        super.initialize()

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun processSystem() {
        game.batch.begin()
        game.batch.draw(game.assets.bg, 0f, 0f)
        renderGems()
        game.batch.end()
    }

    private fun renderGems() {
        for (i in IntStream.range(0, 8)) {
            for (j in IntStream.range(0, 8)) {
                board.cells[i][j].let {
                    if (board.cells[i][j].match >= 1) {
                        game.batch.setColor(game.batch.color.r, game.batch.color.g, game.batch.color.b, .3f)
                    }
                    game.batch.draw(it.texture, it.x, it.y)
                    game.batch.setColor(game.batch.color.r, game.batch.color.g, game.batch.color.b, 1f)
                }
            }
        }
    }
}