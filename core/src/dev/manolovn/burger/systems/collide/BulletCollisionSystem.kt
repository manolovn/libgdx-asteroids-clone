package dev.manolovn.burger.systems.collide

import com.artemis.ComponentMapper
import com.artemis.Entity
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.artemis.systems.EntityProcessingSystem
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.systems.entity.Group.BULLET
import dev.manolovn.burger.systems.entity.Tag.SHIP
import dev.manolovn.burger.util.EntityFactory

@All(Collision::class)
class BulletCollisionSystem : EntityProcessingSystem() {

    private lateinit var collisionSystem: CollisionSystem
    private lateinit var tagManager: TagManager
    private lateinit var groupManager: GroupManager

    private lateinit var posMapper: ComponentMapper<Pos>

    override fun process(e: Entity) {
        val ship = tagManager.getEntity(SHIP)
        if (ship?.id == e.id) return
        if (groupManager.isInGroup(e, BULLET)) return

        groupManager.getEntities(BULLET).forEach { bullet ->
            if (collisionSystem.collides(bullet, e)) {
                bullet.deleteFromWorld()
                e.deleteFromWorld()
                EntityFactory.explosion(world, posMapper[e])
            }
        }
    }
}
