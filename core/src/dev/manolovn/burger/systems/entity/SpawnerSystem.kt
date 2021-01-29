package dev.manolovn.burger.systems.entity

import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import dev.manolovn.burger.systems.entity.Group.ASTEROID
import dev.manolovn.burger.systems.entity.Group.PLAYER
import dev.manolovn.burger.systems.entity.Tag.SHIP
import dev.manolovn.burger.systems.render.AssetsSystem
import dev.manolovn.burger.util.EntityFactory
import net.mostlyoriginal.api.system.core.PassiveSystem

class SpawnerSystem : PassiveSystem() {

    private lateinit var assets: AssetsSystem
    private lateinit var tagManager: TagManager
    private lateinit var groupManager: GroupManager

    override fun initialize() {
        EntityFactory.bg(world, assets.bg)
        initSpaceship()
        initAsteroids()
    }

    private fun initSpaceship() {
        val ship = EntityFactory.ship(world, assets.spaceship)
        tagManager.register(SHIP, ship)
        groupManager.add(ship, PLAYER)
    }

    private fun initAsteroids() {
        for (i in 0..10) {
            buildAsteroidEntity()
        }
    }

    private fun buildAsteroidEntity() {
        val asteroid = EntityFactory.asteroid(world, assets.gems)
        groupManager.add(asteroid, ASTEROID)
    }
}
