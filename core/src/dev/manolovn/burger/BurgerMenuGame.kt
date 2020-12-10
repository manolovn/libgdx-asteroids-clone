package dev.manolovn.burger

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.domain.Piece
import dev.manolovn.burger.domain.swap
import dev.manolovn.burger.util.Assets
import java.util.stream.IntStream.range
import kotlin.math.abs
import kotlin.random.Random

class BurgerMenuGame : ApplicationAdapter(), InputProcessor {

    private lateinit var batch: SpriteBatch
    private lateinit var assets: Assets
    private lateinit var background: Texture
    private lateinit var camera: OrthographicCamera

    private var board: Array<Array<Piece>> = arrayOf()
    val xOffset = 48
    val yOffset = 24
    val cellSize = 54

    override fun create() {
        batch = SpriteBatch()
        assets = Assets()
        background = Texture("background.png")
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.projectionMatrix = camera.combined

        assets.loadAll()

        buildBoard()

        Gdx.input.inputProcessor = this
    }

    private fun buildBoard() {
        board = Array(8) { i ->
            Array(8) { j ->
                Piece(cellSize.toFloat() * i + xOffset, cellSize.toFloat() * j + yOffset, i, j, assets.gems[Random.nextInt(assets.gems.size)])
            }
        }
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()

        batch.draw(background, 0f, 0f)
        renderGems()

        batch.end()
        camera.update()
    }

    private fun renderGems() {
        for (i in range(0, 8)) {
            for (j in range(0, 8)) {
                board[i][j].let {
                    batch.draw(it.texture, it.x, it.y)
                }
            }
        }
    }

    override fun dispose() {
        batch.dispose()
        background.dispose()
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
        x0 = (screenX - xOffset) / cellSize
        y0 = (screenY - yOffset) / cellSize
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        x = (screenX - xOffset) / cellSize
        y = (screenY - yOffset) / cellSize
        if (abs(x - x0) + abs(y - y0) == 1) {
            board[x][y].swap(board[x0][y0])
            val tmp = board[x][y]
            board[x][y] = board[x0][y0]
            board[x0][y0] = tmp
        }
        println("swaping $x $y <-> $x0 $y0")
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