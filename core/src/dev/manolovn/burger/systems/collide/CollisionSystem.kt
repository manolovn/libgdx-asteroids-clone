package dev.manolovn.burger.systems.collide

import com.artemis.ComponentMapper
import com.artemis.Entity
import com.artemis.annotations.All
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Color
import dev.manolovn.burger.components.Pos
import net.mostlyoriginal.api.system.core.PassiveSystem
import kotlin.math.pow
import kotlin.math.sqrt

@All(Collision::class)
class CollisionSystem : PassiveSystem() {

    private lateinit var collisionMapper: ComponentMapper<Collision>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var colorMapper: ComponentMapper<Color>

    fun collides(a: Entity, b: Entity): Boolean {
        val id1 = a.id
        val id2 = b.id
        if (id1 == 0 || id2 == 0) return false
        val pos1 = posMapper[id1]
        val pos2 = posMapper[id2]
        val r1 = collisionMapper[id1].radius
        val r2 = collisionMapper[id2].radius
        val d = sqrt(
            (pos1.x - pos2.x).toDouble().pow(2.0)
                    + (pos1.y - pos2.y).toDouble().pow(2.0)
        )
        if (d < r1 + r2) {
            colorMapper[id1].color = com.badlogic.gdx.graphics.Color.RED
            colorMapper[id2].color = com.badlogic.gdx.graphics.Color.RED
            return true
        }
        return false
    }
}
