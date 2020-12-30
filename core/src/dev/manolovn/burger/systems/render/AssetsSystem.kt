package dev.manolovn.burger.systems.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import net.mostlyoriginal.api.system.core.PassiveSystem
import java.util.HashMap
import java.util.stream.IntStream.range

class AssetsSystem : PassiveSystem() {

    private lateinit var gemsTexture: Sprite

    var sprites = HashMap<String, Animation<TextureRegion>>()

    lateinit var gems: MutableList<Sprite>
    lateinit var bg: Sprite
    lateinit var spaceship: Sprite
    lateinit var bullet: Sprite
    lateinit var explosion: Texture

    private fun loadSprite(path: String): Sprite = Sprite(Texture(Gdx.files.internal(path)))
    private fun loadTexture(path: String): Texture = Texture(Gdx.files.internal(path))

    override fun initialize() {
        bg = loadSprite("space-bg.jpg")
        gemsTexture = loadSprite("gems.png")
        spaceship = loadSprite("spaceship.png")
        bullet = loadSprite("bullet.png")

        explosion = loadTexture("explosion.png")

        add("explosion", 0, 0, 256, 256, 4, 4, explosion, .05f)

        gems = mutableListOf()
        for (i in range(0, 6)) {
            gems.add(Sprite(TextureRegion(gemsTexture, i * 48, 0, 54, 54)))
        }
    }

    private fun add(id: String,
        x1: Int,
        y1: Int,
        w: Int,
        h: Int,
        repeatX: Int,
        repeatY: Int,
        texture: Texture?,
        frameDuration: Float
    ) {
        val regions = arrayOfNulls<TextureRegion>(repeatX * repeatY)
        var count = 0
        for (y in 0 until repeatY) {
            for (x in 0 until repeatX) {
                regions[count++] = TextureRegion(texture, x1 + w * x, y1 + h * y, w, h)
            }
        }
        sprites[id] = Animation(frameDuration, *regions)
    }

    override fun dispose() {
        sprites.clear()
    }
}