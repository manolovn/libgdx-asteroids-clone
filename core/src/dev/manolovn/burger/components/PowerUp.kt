package dev.manolovn.burger.components

import com.artemis.Component

class PowerUp(
    var type: PowerUpType = PowerUpType.NONE
) : Component()

enum class PowerUpType {
    NONE, // nothing
    SPEED, // ship turn speed increased
    FREEZE, // freeze asteroids for 10 seconds
    SHIELD, // shield around the ship
}
