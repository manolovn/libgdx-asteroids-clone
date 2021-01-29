package dev.manolovn.burger.components

import com.artemis.Component

data class Shader(
    var vertex: String = "",
    var fragment: String = "",
) : Component()
