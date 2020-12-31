package dev.manolovn.burger.systems.render

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.BurgerMenuGame.Companion.H
import dev.manolovn.burger.BurgerMenuGame.Companion.W
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Physics
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.components.asVector
import dev.manolovn.burger.systems.entity.SpawnerSystem.Group

@All(Physics::class)
class PhysicsSystem : IteratingSystem() {

    private lateinit var groupManager: GroupManager

    private lateinit var physMapper: ComponentMapper<Physics>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var angleMapper: ComponentMapper<Angle>

    override fun process(entityId: Int) {
        val phys = physMapper[entityId]
        val p = posMapper[entityId]
        val ang = angleMapper[entityId]

        val pos = p.asVector()
        pos.add(phys.v.cpy().scl(world.delta * .5f))
        if (!groupManager.isInGroup(entityId, Group.BULLET)) {
            wrap(pos)
        }
        p.x = pos.x
        p.y = pos.y
        ang.value += world.delta
    }

    private fun wrap(v: Vector2) {
        if (v.x < 0) v.x += W
        if (v.x >= W) v.x -= W
        if (v.y < 0) v.y += H
        if (v.y >= H) v.y -= H
    }
}
