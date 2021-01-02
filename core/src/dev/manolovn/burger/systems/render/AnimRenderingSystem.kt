package dev.manolovn.burger.systems.render

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import dev.manolovn.burger.components.Anim
import dev.manolovn.burger.components.Pos

@All(Anim::class)
class AnimRenderingSystem(
    private val batch: SpriteBatch,
) : EntitySystem() {

    private lateinit var assets: AssetsSystem
    private lateinit var animMapper: ComponentMapper<Anim>
    private lateinit var posMapper: ComponentMapper<Pos>

    override fun begin() {
        batch.begin()
    }

    override fun processSystem() {
        entities.forEach {
            val anim = animMapper[it]
            val pos = posMapper[it]
            anim.age += world.delta
            drawAnimation(anim, pos, anim.id)
        }
    }

    override fun end() {
        batch.end()
    }

    private fun drawAnimation(anim: Anim, position: Pos, id: String) {
        val animation: Animation<TextureRegion> = assets.sprites[id] ?: return
        val frame: TextureRegion = animation.getKeyFrame(anim.age, anim.looping)
        val w = frame.regionWidth.toFloat()
        val h = frame.regionHeight.toFloat()
        batch.draw(
            frame,
            position.x - w / 2,
            position.y - h / 2,
            w / 2,
            h / 2,
            w,
            h,
            1f, 1f, 0f
        )
    }
}
