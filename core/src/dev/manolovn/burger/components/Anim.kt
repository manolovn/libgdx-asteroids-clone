package dev.manolovn.burger.components

import com.artemis.Component

data class Anim(
    var id: String = "",
    var looping: Boolean = false,
    var age: Float = 0f
) : Component()
