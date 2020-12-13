package dev.manolovn.burger.systems

import com.artemis.Aspect
import com.artemis.Entity
import com.artemis.annotations.Wire
import com.artemis.systems.EntityProcessingSystem
import dev.manolovn.burger.components.Board
import dev.manolovn.burger.components.Piece

@Wire
class BoardSpawnerSystem : EntityProcessingSystem(
        Aspect.all(Piece::class.java, Board::class.java)
) {

    override fun process(e: Entity?) {

    }
}