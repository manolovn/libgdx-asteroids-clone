package dev.manolovn.burger.systems

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import dev.manolovn.burger.BurgerMenuGame

@All
class CameraSystem(private val game: BurgerMenuGame): EntitySystem() {

    private var camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    override fun initialize() {
        super.initialize()
        camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        game.batch.projectionMatrix = camera.combined
    }

    override fun processSystem() {
        camera.update()
    }
}