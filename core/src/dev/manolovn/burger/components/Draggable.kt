package dev.manolovn.burger.components

import com.artemis.Component
import com.badlogic.gdx.math.Vector2

data class Draggable(
        var downPoint: Vector2 = Vector2(0f, 0f),
        var upPoint: Vector2 = Vector2(0f, 0f)
): Component()
