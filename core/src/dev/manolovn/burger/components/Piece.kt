package dev.manolovn.burger.components

import com.artemis.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Piece(
        var x: Float = 0f,
        var y: Float = 0f,
        var row: Int = 0,
        var col: Int = 0,
        var texture: TextureRegion? = null,
        var type: Int = 0,
        var match: Int = 0
        ) : Component()

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