package dev.manolovn.burger.systems

import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import dev.manolovn.burger.components.Board
import dev.manolovn.burger.components.Piece

@All(Piece::class)
class MatchingSystem: IteratingSystem() {

    private lateinit var board: Board

    override fun process(entityId: Int) {
        board.checkMatches()
        board.update()
    }
}