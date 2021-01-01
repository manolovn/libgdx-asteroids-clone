package dev.manolovn.burger.systems.collide

import com.artemis.ComponentMapper
import com.artemis.Entity
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.artemis.systems.EntityProcessingSystem
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.systems.entity.SpawnerSystem.Group.ASTEROID
import dev.manolovn.burger.systems.entity.SpawnerSystem.Tag.SHIP
import dev.manolovn.burger.util.EntityFactory

@All(Collision::class)
class AsteroidCollisionSystem : EntityProcessingSystem() {

    private lateinit var collisionSystem: CollisionSystem
    private lateinit var tagManager: TagManager
    private lateinit var groupManager: GroupManager

    private lateinit var posMapper: ComponentMapper<Pos>

    override fun process(e: Entity) {
        val ship = tagManager.getEntity(SHIP) ?: return
        if (!groupManager.isInGroup(e, ASTEROID)) return

        groupManager.getEntities(ASTEROID).forEach { asteroid ->
            if (collisionSystem.collides(asteroid, ship)) {
                EntityFactory.explosion(world, posMapper[ship])
                ship.deleteFromWorld()
            }
        }
    }
}
