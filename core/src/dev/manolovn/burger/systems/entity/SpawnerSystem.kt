package dev.manolovn.burger.systems.entity

import com.artemis.annotations.Wire
import com.artemis.managers.TagManager
import dev.manolovn.burger.systems.entity.SpawnerSystem.Tag.ASTEROID
import dev.manolovn.burger.systems.entity.SpawnerSystem.Tag.SHIP
import dev.manolovn.burger.systems.render.AssetsSystem
import dev.manolovn.burger.util.EntityFactory
import net.mostlyoriginal.api.system.core.PassiveSystem

@Wire
class SpawnerSystem : PassiveSystem() {

    private lateinit var assets: AssetsSystem
    private lateinit var tagManager: TagManager

    override fun initialize() {
        EntityFactory.bg(world, assets.bg)
        initSpaceship()
        initAsteroids()
    }

    private fun initSpaceship() {
        val ship = EntityFactory.ship(world, assets.spaceship)
        tagManager.register(SHIP, ship)
    }

    private fun initAsteroids() {
        for (i in 0..10) {
            buildAsteroidEntity()
        }
    }

    private fun buildAsteroidEntity() {
        val asteroid = EntityFactory.asteroid(world, assets.gems)
        tagManager.register(ASTEROID, asteroid)
    }

    object Tag {
        const val ASTEROID: String = "asteroid"
        const val SHIP: String = "ship"
    }

    object Group {
        const val BULLET: String = "bullet"
    }
}
