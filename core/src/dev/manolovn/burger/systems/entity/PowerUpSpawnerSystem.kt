package dev.manolovn.burger.systems.entity

import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IntervalEntitySystem
import dev.manolovn.burger.systems.entity.Group.POWERUP
import dev.manolovn.burger.util.EntityFactory

@All
class PowerUpSpawnerSystem : IntervalEntitySystem(INTERVAL_IN_SECONDS) {

    private lateinit var groupManager: GroupManager

    override fun processSystem() {
        val powerUp = EntityFactory.powerUp(world)
        groupManager.add(powerUp, POWERUP)
    }

    private companion object {
        const val INTERVAL_IN_SECONDS = 5f
    }
}
