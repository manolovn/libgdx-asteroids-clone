package dev.manolovn.burger.systems.render

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.graphics.OrthographicCamera
import dev.manolovn.burger.BurgerMenuGame

@All
class CameraSystem(private val game: BurgerMenuGame): EntitySystem() {

    private var camera = OrthographicCamera(graphics.width.toFloat(), graphics.height.toFloat())

    override fun initialize() {
        super.initialize()
        camera.setToOrtho(false, graphics.width.toFloat(), graphics.height.toFloat())
        game.batch.projectionMatrix = camera.combined
    }

    override fun processSystem() {
        camera.update()
    }
}