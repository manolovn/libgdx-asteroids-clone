package dev.manolovn.burger.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import java.util.stream.IntStream.range

class Assets {

    private lateinit var gemsTexture: Texture
    lateinit var gems: MutableList<TextureRegion>

    fun loadTexture(path: String): Texture = Texture(Gdx.files.internal(path))

    fun loadAll() {
        gemsTexture = loadTexture("gems.png")

        gems = mutableListOf()
        for (i in range(0, 6)) {
            gems.add(TextureRegion(gemsTexture, i * 48, 0, 54, 54))
        }
    }
}