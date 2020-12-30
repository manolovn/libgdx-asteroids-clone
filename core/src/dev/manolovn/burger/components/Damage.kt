package dev.manolovn.burger.components

import com.artemis.Component

data class Damage(
    var weight: Int = 1,
    var resistence: Int = 1
) : Component()
