package dev.manolovn.burger.systems

import com.artemis.BaseSystem
import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import dev.manolovn.burger.components.Board
import dev.manolovn.burger.components.Draggable
import dev.manolovn.burger.components.Pos
import kotlin.math.abs

@All(Draggable::class, Pos::class)
class MouseSystem : BaseSystem(), InputProcessor {

    private lateinit var draggableMapper: ComponentMapper<Draggable>

    override fun initialize() {
        super.initialize()
        Gdx.input.inputProcessor = this
    }

    override fun processSystem() {

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
            //board.swap(x, y, x0, y0)
        }
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false

    override fun scrolled(amountX: Float, amountY: Float): Boolean = false
}