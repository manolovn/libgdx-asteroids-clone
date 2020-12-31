package dev.manolovn.burger.components

import com.artemis.Component
import com.badlogic.gdx.math.Vector2

data class Pos(
        var x: Float = 0f,
        var y: Float = 0f
) : Component()

fun Pos.asVector() : Vector2 = Vector2(x, y)
