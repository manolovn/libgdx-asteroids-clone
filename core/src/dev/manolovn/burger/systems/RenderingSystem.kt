package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.*

@All(Renderable::class)
class RenderingSystem(private val game: BurgerMenuGame): EntitySystem() {

    private lateinit var spriteMapper: ComponentMapper<Sprite>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var matchMapper: ComponentMapper<Matcheable>

    override fun initialize() {
        super.initialize()

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun begin() {
        game.batch.begin()
    }

    override fun processSystem() {
        entities.forEach {
            val sprite = spriteMapper[it]
            val pos = posMapper[it]
            val match = matchMapper[it]
            if (match.match >= 1) {
                game.batch.setColor(game.batch.color.r, game.batch.color.g, game.batch.color.b, .3f)
            } else {
                game.batch.setColor(game.batch.color.r, game.batch.color.g, game.batch.color.b, 1f)
            }
            game.batch.draw(sprite.texture, pos.x, pos.y)
        }
    }

    override fun end() {
        game.batch.end()
    }
}
