package dev.manolovn.burger.systems.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import net.mostlyoriginal.api.system.core.PassiveSystem
import java.util.*
import java.util.stream.IntStream.range

class AssetsSystem : PassiveSystem() {

    private lateinit var gemsTexture: Sprite

    var sprites = HashMap<String, Animation<TextureRegion>>()

    lateinit var gems: MutableList<Sprite>
    lateinit var bg: Sprite
    lateinit var spaceship: Sprite
    lateinit var bullet: Sprite

    lateinit var explosion: Texture
    lateinit var powerups: Texture

    lateinit var font: BitmapFont
    lateinit var fontSmall: BitmapFont

    private fun loadSprite(path: String): Sprite = Sprite(Texture(Gdx.files.internal(path)))
    private fun loadTexture(path: String): Texture = Texture(Gdx.files.internal(path))

    override fun initialize() {
        bg = loadSprite("space-bg.jpg")
        gemsTexture = loadSprite("gems.png")
        spaceship = loadSprite("spaceship.png")
        bullet = loadSprite("bullet.png")

        explosion = loadTexture("exp.png")
        powerups = loadTexture("powerups.png")

        loadFont()

        add("explosion", 0, 0, 64, 64, 4, 4, explosion, .05f)
        add("powerup-1", 0, 0, 32, 32, 8, 2, powerups, .05f)
        add("powerup-2", 0, 64, 32, 32, 8, 2, powerups, .05f)
        add("powerup-3", 0, 128, 32, 32, 8, 2, powerups, .05f)

        gems = mutableListOf()
        for (i in range(0, 6)) {
            gems.add(Sprite(TextureRegion(gemsTexture, i * 48, 0, 54, 54)))
        }
    }

    private fun loadFont() {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("font/floppy.ttf"))
        with(generator) {
            font = generateFont(FreeTypeFontParameter().also {
                it.size = 50
                it.borderWidth = 1f
                it.color = Color.YELLOW
            })
            fontSmall = generateFont(FreeTypeFontParameter().also {
                it.size = 25
                it.color = Color.WHITE
            })
            dispose()
        }
    }

    private fun add(
        id: String,
        x1: Int,
        y1: Int,
        w: Int,
        h: Int,
        repeatX: Int,
        repeatY: Int,
        texture: Texture?,
        frameDuration: Float,
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