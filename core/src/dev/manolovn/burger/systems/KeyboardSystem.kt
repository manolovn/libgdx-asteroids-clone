package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Control

@All(Angle::class, Control::class)
class KeyboardSystem : IteratingSystem() {

    lateinit var angleMapper: ComponentMapper<Angle>

    override fun process(entityId: Int) {
        val angle = angleMapper[entityId]
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angle.value += speed * world.delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angle.value -= speed * world.delta
        }
    }

    companion object {
        const val speed = 2.5f
    }
}