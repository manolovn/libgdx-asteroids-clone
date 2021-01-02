package dev.manolovn.burger.systems.render

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.manolovn.burger.AsteroidsGame.Companion.H
import dev.manolovn.burger.AsteroidsGame.Companion.W
import net.mostlyoriginal.api.system.core.PassiveSystem

class CameraSystem(
    private val batch: SpriteBatch,
) : PassiveSystem() {

    lateinit var camera: OrthographicCamera

    override fun initialize() {
        camera = OrthographicCamera(W, H)
        camera.setToOrtho(false, W, H)
        batch.projectionMatrix = camera.combined
        camera.update()
    }
}