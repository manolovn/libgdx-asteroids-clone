package dev.manolovn.burger.components

import com.artemis.Component

data class Matcheable(
        var row: Int = 0,
        var col: Int = 0,
        var type: Int = 0,
        var match: Int = 0
) : Component()
