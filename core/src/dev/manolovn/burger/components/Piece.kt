package dev.manolovn.burger.components

import com.artemis.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Piece(
        var x: Float,
        var y: Float,
        var row: Int,
        var col: Int,
        var texture: TextureRegion,
        var type: Int,
        var match: Int = 0
        ) : Component() {
}

fun Piece.swap(p: Piece) {
    val tmp = this.col
    this.col = p.col
    p.col = tmp

    val tmpr = this.row
    this.row = p.row
    p.row = tmpr

    val tx = this.x
    this.x = p.x
    p.x = tx

    val ty = this.y
    this.y = p.y
    p.y = ty
}