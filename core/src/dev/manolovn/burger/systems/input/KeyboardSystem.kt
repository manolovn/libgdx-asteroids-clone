package dev.manolovn.burger.systems.input

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Control
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.components.Ship
import dev.manolovn.burger.systems.entity.Group
import dev.manolovn.burger.systems.render.AssetsSystem
import dev.manolovn.burger.util.EntityFactory

@All(Ship::class, Angle::class, Control::class)
class KeyboardSystem : IteratingSystem() {

    private lateinit var assets: AssetsSystem
    private lateinit var groupManager: GroupManager

    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var controlMapper: ComponentMapper<Control>

    override fun process(entityId: Int) {
        val pos = posMapper[entityId]
        val angle = angleMapper[entityId]
        val control = controlMapper[entityId]

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angle.value += control.turnSpeed * world.delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angle.value -= control.turnSpeed * world.delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            shoot(pos, angle)
        }
    }

    private fun shoot(pos: Pos, angle: Angle) {
        EntityFactory.bullet(world, pos, angle, assets.bullet).also {
            groupManager.add(it, Group.BULLET)
        }
    }
}