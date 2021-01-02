package dev.manolovn.burger.systems.render

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

@All
class CameraShakeSystem : EntitySystem() {

    lateinit var cameraSystem: CameraSystem

    var shake = 0f
    var push = Vector2()

    fun shake(pixels: Float) {
        shake = pixels
    }

    fun push(x: Float, y: Float) {
        push.x = x
        push.y = y
    }

    override fun processSystem() {
        val camera = cameraSystem.camera
        if (shake != 0f) {
            camera.position.x =
                (camera.position.x + MathUtils.random(push.x)
                        + if (shake != 0f) MathUtils.random(-shake, shake) else 0f)
            camera.position.y =
                (camera.position.y + MathUtils.random(push.y)
                        + if (shake != 0f) MathUtils.random(-shake, shake) else 0f)
            camera.update()
            if (shake > 0) {
                shake -= world.delta * 4f
                if (shake < 0) shake = 0f
            }
            decrease(push, world.delta * 16f)
        }
    }

    private fun decrease(v: Vector2, delta: Float) {
        if (v.x > 0) {
            v.x -= delta
            if (v.x < 0) {
                v.x = 0f
            }
        }
        if (v.x < 0) {
            v.x += delta
            if (v.x > 0) {
                v.x = 0f
            }
        }
        if (v.y > 0) {
            v.y -= delta
            if (v.y < 0) {
                v.y = 0f
            }
        }
        if (v.y < 0) {
            v.y += delta
            if (v.y > 0) {
                v.y = 0f
            }
        }
    }
}