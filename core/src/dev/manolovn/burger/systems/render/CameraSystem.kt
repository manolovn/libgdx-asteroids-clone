package dev.manolovn.burger.systems.render

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

@All
class CameraSystem(
    private val batch: SpriteBatch
) : EntitySystem() {

    private var camera = OrthographicCamera(graphics.width.toFloat(), graphics.height.toFloat())

    override fun initialize() {
        camera.setToOrtho(false, graphics.width.toFloat(), graphics.height.toFloat())
        batch.projectionMatrix = camera.combined
    }

    override fun processSystem() {
        camera.update()
    }
}