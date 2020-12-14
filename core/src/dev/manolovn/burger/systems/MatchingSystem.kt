package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import dev.manolovn.burger.components.Board
import dev.manolovn.burger.components.Matcheable
import java.util.stream.IntStream

@All(Matcheable::class)
class MatchingSystem: EntitySystem() {

    private lateinit var matchMapper: ComponentMapper<Matcheable>

    private var cells: Array<Array<Int>> = arrayOf()

    override fun begin() {
        cells = Array(8) { Array(8) { -1 } }
    }

    override fun processSystem() {
        entities.forEach {
            val c = matchMapper[it]
            cells[c.col][c.row] = c.type
        }

        checkMatches()
        update()
    }

    private fun checkMatches() {
        for (i in IntStream.range(0, Board.COLS)) {
            for (j in IntStream.range(0, Board.ROWS)) {
                if (i < Board.COLS - 1 && cells[i][j] == cells[i + 1][j]) {
                    if (i > 0 && cells[i][j] == cells[i - 1][j]) {
                        for (n in -1..1) {
                            //cells[i + n][j].match++
                            println("match")
                        }
                    }
                }
                if (j < Board.ROWS - 1 && cells[i][j] == cells[i][j + 1]) {
                    if (j > 0 && cells[i][j] == cells[i][j - 1]) {
                        for (n in -1..1) {
                            //cells[i][j + n].match++
                            println("match")
                        }
                    }
                }
            }
        }
    }

    fun update() {
        /*for (i in range(0, COLS)) {
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
        }*/
    }
}