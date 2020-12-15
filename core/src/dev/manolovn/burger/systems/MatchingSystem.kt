package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import dev.manolovn.burger.components.Board.Companion.COLS
import dev.manolovn.burger.components.Board.Companion.ROWS
import dev.manolovn.burger.components.Color
import dev.manolovn.burger.components.Matcheable
import dev.manolovn.burger.components.Pos
import java.util.stream.IntStream.range

@All(Matcheable::class, Color::class, Pos::class)
class MatchingSystem: EntitySystem() {

    private lateinit var matchMapper: ComponentMapper<Matcheable>
    private lateinit var colorMapper: ComponentMapper<Color>
    private lateinit var posMapper: ComponentMapper<Pos>

    private var cells: Array<Array<Matcheable?>> = arrayOf()
    private var aux: Array<Array<Color?>> = arrayOf()
    private var posm: Array<Array<Pos?>> = arrayOf()

    override fun begin() {
        cells = Array(8) { Array(8) { null } }
        aux = Array(8) { Array(8) { null } }
        posm = Array(8) { Array(8) { null } }
    }

    override fun processSystem() {
        entities.forEach {
            val m = matchMapper[it]
            cells[m.col][m.row] = m
            val c = colorMapper[it]
            aux[m.col][m.row] = c
            val p = posMapper[it]
            posm[m.col][m.row] = p
        }

        checkMatches()
        update()
    }

    private fun checkMatches() {
        for (i in range(0, COLS)) {
            for (j in range(0, ROWS)) {
                if (i < COLS - 1 && cells[i][j]?.type == cells[i + 1][j]?.type) {
                    if (i > 0 && cells[i][j]?.type == cells[i - 1][j]?.type) {
                        for (n in -1..1) {
                            cells[i + n][j]!!.match++
                            aux[i + n][j]!!.color.a = .3f
                        }
                    }
                }
                if (j < ROWS - 1 && cells[i][j]?.type == cells[i][j + 1]?.type) {
                    if (j > 0 && cells[i][j]?.type == cells[i][j - 1]?.type) {
                        for (n in -1..1) {
                            cells[i][j + n]!!.match++
                            aux[i][j + n]!!.color.a = .3f
                        }
                    }
                }
            }
        }
    }

    private fun update() {
        for (i in range(0, COLS)) {
            for (j in range(0, ROWS)) {
                if (cells[i][j]!!.match >= 1) {
                    for (n in range(i, 1)) {
                        if (cells[i][j]!!.match <= 0) {
                            swap(cells[n][j]!!, cells[i][j]!!)
                            swap(posm[n][j]!!, posm[i][j]!!)
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
                if (cells[i][j]!!.match >= 1) {
                    /*val kind = Random.nextInt(assets.gems.size)
                    cells[i][j].type = kind
                    cells[i][j].texture = assets.gems[kind]
                    cells[i][j].match = 0*/
                    n++
                }
                i--
            }
        }
    }

    private fun swap(o: Matcheable, p: Matcheable) {
        val tmp = o.col
        o.col = p.col
        p.col = tmp

        val tmpr = o.row
        o.row = p.row
        p.row = tmpr
    }

    private fun swap(o: Pos, p: Pos) {
        val tx = o.x
        o.x = p.x
        p.x = tx

        val ty = o.y
        o.y = p.y
        p.y = ty
    }
}