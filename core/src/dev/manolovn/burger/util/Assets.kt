package dev.manolovn.burger.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import java.util.stream.IntStream.range

class Assets {

    private lateinit var gemsTexture: Sprite

    lateinit var gems: MutableList<Sprite>
    lateinit var bg: Sprite
    lateinit var spaceship: Sprite
    lateinit var bullet: Sprite

    private fun loadTexture(path: String): Sprite = Sprite(Texture(Gdx.files.internal(path)))

    fun loadAll(): Assets {
        bg = loadTexture("space-bg.jpg")
        gemsTexture = loadTexture("gems.png")
        spaceship = loadTexture("spaceship.png")
        bullet = loadTexture("bullet.png")

        gems = mutableListOf()
        for (i in range(0, 6)) {
            gems.add(Sprite(TextureRegion(gemsTexture, i * 48, 0, 54, 54)))
        }

        return this
    }

    fun dispose() {

    }
}