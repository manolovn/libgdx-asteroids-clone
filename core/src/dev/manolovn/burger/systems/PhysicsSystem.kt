package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.Angle
import dev.manolovn.burger.components.Physics
import dev.manolovn.burger.components.Pos

@All(Physics::class)
class PhysicsSystem(private val game: BurgerMenuGame) : IteratingSystem() {

    private lateinit var physMapper: ComponentMapper<Physics>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var angleMapper: ComponentMapper<Angle>

    override fun process(entityId: Int) {
        val phys = physMapper[entityId]
        val p = posMapper[entityId]
        val pos = Vector2(p.x, p.y)
        val ang = angleMapper[entityId]

        pos.add(phys.v.cpy().scl(world.delta * .5f))
        p.x = pos.x
        p.y = pos.y
        ang.value += world.delta
    }
}
