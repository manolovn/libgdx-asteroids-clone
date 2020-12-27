package dev.manolovn.burger.components

import com.artemis.Component
import com.badlogic.gdx.math.Vector2

data class Physics(
    var v: Vector2 = Vector2.Zero
) : Component()
