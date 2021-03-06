package dev.manolovn.burger.systems.render

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import dev.manolovn.burger.components.*

@All(MapElement::class)
class MapRenderingSystem(private val batch: SpriteBatch) : EntitySystem() {

    private lateinit var cameraSystem: CameraSystem

    private lateinit var spriteMapper: ComponentMapper<Sprite>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var colorMapper: ComponentMapper<Color>
    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var scaleMapper: ComponentMapper<Scale>

    override fun begin() {
        batch.projectionMatrix = cameraSystem.camera.combined
        batch.begin()
    }

    override fun processSystem() {
        entities.forEach {
            val sprite = spriteMapper[it]
            val pos = posMapper[it]
            val c = colorMapper[it]
            val rot = angleMapper[it]
            val scale = scaleMapper[it]
            batch.color = c.color
            val degs: Float = rot.value * MathUtils.radDeg
            val w = sprite.texture!!.width
            val h = sprite.texture!!.height
            batch.draw(
                sprite.texture, pos.x, pos.y, w / 2, h / 2, w,
                h, scale.x, scale.y, degs
            )
        }
    }

    override fun end() {
        batch.end()
    }
}