package dev.manolovn.burger.components

class Board {

    var cells: Array<Array<Int>> = arrayOf()

    fun swap(x: Int, y: Int, x0: Int, y0: Int) {
        /*cells[x][y].swap(cells[x0][y0])
        val tmp = cells[x][y]
        cells[x][y] = cells[x0][y0]
        cells[x0][y0] = tmp*/
    }

    companion object {
        const val COLS = 8
        const val ROWS = 8
        const val CELL_SIZE = 54
        const val X_OFFSET = 48
        const val Y_OFFSET = 24
    }
}