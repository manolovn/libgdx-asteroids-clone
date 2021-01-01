package dev.manolovn.burger.systems.input

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import dev.manolovn.burger.BurgerMenuGame.Companion.W
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Control
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.components.Ship
import dev.manolovn.burger.systems.entity.SpawnerSystem
import dev.manolovn.burger.systems.render.AssetsSystem
import dev.manolovn.burger.util.EntityFactory

@All(Ship::class, Angle::class, Control::class)
class TouchSystem : IteratingSystem() {

    private lateinit var groupManager: GroupManager
    private lateinit var assets: AssetsSystem

    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var controlMapper: ComponentMapper<Control>

    override fun process(entityId: Int) {
        val angle = angleMapper[entityId]
        val control = controlMapper[entityId]

        if (Gdx.input.isTouched) {
            if (Gdx.input.x > 3 * W / 4) {
                angle.value -= control.turnSpeed * world.delta
            }
            if (Gdx.input.x < W / 4) {
                angle.value += control.turnSpeed * world.delta
            }
            if (Gdx.input.x > W / 4 && Gdx.input.x < 3 * W / 4) {
                shoot(entityId)
            }
        }
    }

    private fun shoot(id: Int) {
        val angle = angleMapper[id]
        val pos = posMapper[id]
        EntityFactory.bullet(world, pos, angle, assets.bullet).also {
            groupManager.add(it, SpawnerSystem.Group.BULLET)
        }
    }
}