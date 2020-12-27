package dev.manolovn.burger.systems

import com.artemis.BaseEntitySystem
import com.artemis.ComponentMapper
import com.artemis.annotations.All
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Color
import dev.manolovn.burger.components.Pos
import kotlin.math.pow
import kotlin.math.sqrt

@All(Collision::class)
class CollisionSystem : BaseEntitySystem() {

    private lateinit var collisionMapper: ComponentMapper<Collision>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var colorMapper: ComponentMapper<Color>

    override fun processSystem() {
        val entities = subscription.entities
        val ids = entities.data.filter { it >= 0 }
        for (i in ids.indices) {
            for (j in i + 1 until ids.size) {
                val id1 = ids[i]
                val id2 = ids[j]
                if (id1 == 0 || id2 == 0) continue
                val pos1 = posMapper[id1]
                val pos2 = posMapper[id2]
                val r1 = collisionMapper[id1].radius
                val r2 = collisionMapper[id2].radius
                val d = sqrt(
                    (pos1.x - pos2.x).toDouble().pow(2.0)
                            + (pos1.y - pos2.y).toDouble().pow(2.0)
                )
                if (d < r1 + r2) {
                    colorMapper[id1].color = com.badlogic.gdx.graphics.Color.BLACK
                    colorMapper[id2].color = com.badlogic.gdx.graphics.Color.BLACK
                }
            }
        }
    }
}
