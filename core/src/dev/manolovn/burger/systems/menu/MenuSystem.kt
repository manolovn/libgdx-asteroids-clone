package dev.manolovn.burger.systems.menu

import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import dev.manolovn.burger.AsteroidsGame
import dev.manolovn.burger.AsteroidsGame.Companion.H
import dev.manolovn.burger.AsteroidsGame.Companion.W
import dev.manolovn.burger.systems.render.AssetsSystem

@All
class MenuSystem(
    private val game: AsteroidsGame,
    private var batch: SpriteBatch,
) : EntitySystem() {

    private lateinit var assetSystem: AssetsSystem

    override fun begin() {
        val shimmerProgram = ShaderProgram(
            Gdx.files.internal("shader/shimmer.vertex.glsl"),
            Gdx.files.internal("shader/shimmer.fragment.glsl")
        )
        if (!shimmerProgram.isCompiled) throw RuntimeException("Compilation failed." + shimmerProgram.log)
        batch = SpriteBatch(1000, shimmerProgram)
        batch.begin()
    }

    override fun processSystem() {
        val w = assetSystem.logo.width
        val h = assetSystem.logo.height
        batch.draw(assetSystem.logo, (W - w) / 2, (H - h) / 2)
        subtitle("press SPACE to start")
        waitStart()
    }

    private fun subtitle(s: String) {
        with(assetSystem.fontSmall) {
            val glyphLayout = GlyphLayout(this, s)
            draw(batch, s, (AsteroidsGame.W - glyphLayout.width) / 2, 50f)
        }
    }

    private fun waitStart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // restart game
            game.restart()
            return
        }
    }

    override fun end() {
        batch.end()
    }
}
