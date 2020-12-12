package dev.manolovn.burger.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.domain.Board
import java.util.stream.IntStream
import kotlin.math.abs

class InGameScreen(
        private val game: BurgerMenuGame
) : Screen, InputProcessor {

    private var board: Board = Board(game.assets)
    private var camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    override fun show() {
        camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        game.batch.projectionMatrix = camera.combined
        Gdx.input.inputProcessor = this
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        board.checkMatches()
        board.update()

        camera.update()
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

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        game.dispose()
    }

    override fun keyDown(keycode: Int): Boolean = false

    override fun keyUp(keycode: Int): Boolean = false

    override fun keyTyped(character: Char): Boolean = false

    var x = 0
    var x0 = 0
    var y = 0
    var y0 = 0

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        x0 = (screenX - Board.X_OFFSET) / Board.CELL_SIZE
        y0 = (screenY - Board.Y_OFFSET) / Board.CELL_SIZE
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        x = (screenX - Board.X_OFFSET) / Board.CELL_SIZE
        y = (screenY - Board.Y_OFFSET) / Board.CELL_SIZE
        if (abs(x - x0) + abs(y - y0) == 1) {
            board.swap(x, y, x0, y0)
        }
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false

    override fun scrolled(amountX: Float, amountY: Float): Boolean = false
}