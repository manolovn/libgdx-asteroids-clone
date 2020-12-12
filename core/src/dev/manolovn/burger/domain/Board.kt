package dev.manolovn.burger.domain

import dev.manolovn.burger.util.Assets
import java.util.stream.IntStream.range
import kotlin.random.Random

class Board(
        private val assets: Assets
) {

    var cells: Array<Array<Piece>> = arrayOf()

    init {
        cells = Array(COLS) { i ->
            Array(ROWS) { j ->
                val kind = Random.nextInt(assets.gems.size)
                Piece(CELL_SIZE.toFloat() * i + X_OFFSET, CELL_SIZE.toFloat() * j + Y_OFFSET, i, j, assets.gems[kind], kind)
            }
        }
    }

    fun swap(x: Int, y: Int, x0: Int, y0: Int) {
        cells[x][y].swap(cells[x0][y0])
        val tmp = cells[x][y]
        cells[x][y] = cells[x0][y0]
        cells[x0][y0] = tmp
    }

    fun checkMatches() {
        for (i in range(0, COLS)) {
            for (j in range(0, ROWS)) {
                if (i < COLS - 1 && cells[i][j].type == cells[i + 1][j].type) {
                    if (i > 0 && cells[i][j].type == cells[i - 1][j].type) {
                        for (n in -1..1) cells[i + n][j].match++
                    }
                }
                if (j < ROWS - 1 && cells[i][j].type == cells[i][j + 1].type) {
                    if (j > 0 && cells[i][j].type == cells[i][j - 1].type) {
                        for (n in -1..1) cells[i][j + n].match++
                    }
                }
            }
        }
    }

    fun update() {
        for (i in range(0, COLS)) {
            for (j in range(0, ROWS)) {
                if (cells[i][j].match >= 1) {
                    for (n in range(i, 1)) {
                        if (cells[i][j].match <= 0) {
                            swap(n, j, i, j)
                            break
                        }
                    }
                }
            }
        }

        for (j in range(0, COLS)) {
            var i = COLS - 1
            var n = 0
            while (i >= 0) {
                if (cells[i][j].match >= 1) {
                    val kind = Random.nextInt(assets.gems.size)
                    cells[i][j].type = kind
                    cells[i][j].texture = assets.gems[kind]
                    cells[i][j].match = 0
                    n++
                }
                i--
            }
        }
    }

    companion object {
        const val COLS = 8
        const val ROWS = 8
        const val CELL_SIZE = 54
        const val X_OFFSET = 48
        const val Y_OFFSET = 24
    }
}