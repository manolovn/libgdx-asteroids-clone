package dev.manolovn.burger.systems.input

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Control

@All(Angle::class, Control::class)
class KeyboardSystem : IteratingSystem() {

    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var controlMapper: ComponentMapper<Control>

    override fun process(entityId: Int) {
        val angle = angleMapper[entityId]
        val control = controlMapper[entityId]
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angle.value += control.turnSpeed * world.delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angle.value -= control.turnSpeed * world.delta
        }
    }
}