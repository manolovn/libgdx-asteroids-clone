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
    private val xOffset = 48
    private val yOffset = 24
    private val cellSize = 54

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
                val kind = Random.nextInt(assets.gems.size)
                Piece(cellSize.toFloat() * i + xOffset, cellSize.toFloat() * j + yOffset, i, j, assets.gems[kind], kind)
            }
        }
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        checkMatches()
        updateGrid()

        batch.begin()
        batch.draw(background, 0f, 0f)
        renderGems()
        batch.end()
        camera.update()
    }

    private fun updateGrid() {
        for (i in range(0, 8)) {
            for (j in range(0, 8)) {
                if (board[i][j].match >= 1) {
                    for (n in range(i, 1)) {
                        if (board[i][j].match <= 0) {
                            board[n][j].swap(board[i][j])
                            val tmp = board[n][j]
                            board[n][j] = board[i][j]
                            board[i][j] = tmp
                            break
                        }
                    }
                }
            }
        }

        for (j in range(0, 8)) {
            var i = 7
            var n = 0
            while (i >= 0) {
                if (board[i][j].match >= 1) {
                    val kind = Random.nextInt(assets.gems.size)
                    board[i][j].type = kind
                    board[i][j].texture = assets.gems[kind]
                    board[i][j].match = 0
                    n++
                }
                i--
            }
        }
    }

    private fun checkMatches() {
        for (i in range(0, 8)) {
            for (j in range(0, 8)) {
                if (i < 7 && board[i][j].type == board[i + 1][j].type) {
                    if (i > 0 && board[i][j].type == board[i - 1][j].type) {
                        for (n in -1..1) board[i + n][j].match++
                    }
                }
                if (j < 7 && board[i][j].type == board[i][j + 1].type) {
                    if (j > 0 && board[i][j].type == board[i][j - 1].type) {
                        for (n in -1..1) board[i][j + n].match++
                    }
                }
            }
        }
    }

    private fun renderGems() {
        for (i in range(0, 8)) {
            for (j in range(0, 8)) {
                board[i][j].let {
                    if (board[i][j].match >= 1) {
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