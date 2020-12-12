package dev.manolovn.burger

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.domain.Board
import dev.manolovn.burger.util.Assets
import java.util.stream.IntStream.range
import kotlin.math.abs

class BurgerMenuGame : ApplicationAdapter(), InputProcessor {

    private lateinit var batch: SpriteBatch
    private lateinit var assets: Assets
    private lateinit var camera: OrthographicCamera

    private lateinit var board: Board

    override fun create() {
        batch = SpriteBatch()
        assets = Assets().loadAll()
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.projectionMatrix = camera.combined

        board = Board(assets)

        Gdx.input.inputProcessor = this
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        board.checkMatches()
        board.update()

        camera.update()
        batch.begin()
        batch.draw(assets.bg, 0f, 0f)
        renderGems()
        batch.end()
    }

    private fun renderGems() {
        for (i in range(0, 8)) {
            for (j in range(0, 8)) {
                board.cells[i][j].let {
                    if (board.cells[i][j].match >= 1) {
                        batch.setColor(batch.color.r, batch.color.g, batch.color.b, .3f)
                    }
                    batch.draw(it.texture, it.x, it.y)
                    batch.setColor(batch.color.r, batch.color.g, batch.color.b, 1f)
                }
            }
        }
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

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

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }
}