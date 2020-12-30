package dev.manolovn.burger.systems.collide

import com.artemis.ComponentMapper
import com.artemis.Entity
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.artemis.systems.EntityProcessingSystem
import com.artemis.utils.EntityBuilder
import dev.manolovn.burger.components.Anim
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.systems.collide.CollisionSystem

@All(Collision::class)
class BulletCollisionSystem : EntityProcessingSystem() {

    private lateinit var collisionSystem: CollisionSystem
    private lateinit var tagManager: TagManager
    private lateinit var groupManager: GroupManager

    private lateinit var posMapper: ComponentMapper<Pos>

    override fun process(e: Entity) {
        val ship = tagManager.getEntity("ship")
        if (ship.id == e.id) return
        if (groupManager.isInGroup(e, "bullet")) return

        val bullets = groupManager.getEntities("bullet")
        for (bullet in bullets) {
            if (collisionSystem.collides(bullet, e)) {
                bullet.deleteFromWorld()
                e.deleteFromWorld()
                val pos = posMapper[e]
                EntityBuilder(world)
                    .with(pos, Anim(id = "explosion"))
                    .build()
            }
        }
    }
}
