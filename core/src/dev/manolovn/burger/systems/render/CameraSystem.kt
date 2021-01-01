package dev.manolovn.burger.systems.render

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.BurgerMenuGame.Companion.H
import dev.manolovn.burger.BurgerMenuGame.Companion.W

@All
class CameraSystem(
    private val batch: SpriteBatch
) : EntitySystem() {

    private var camera = OrthographicCamera()

    override fun initialize() {
        camera.setToOrtho(false, W, H)
        batch.projectionMatrix = camera.combined
    }

    override fun processSystem() {
        camera.update()
    }
}