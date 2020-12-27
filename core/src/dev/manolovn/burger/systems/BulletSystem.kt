package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.artemis.utils.EntityBuilder
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.*
import kotlin.random.Random

@All(Ship::class)
class BulletSystem(private val game: BurgerMenuGame) : IteratingSystem() {

    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var posMapper: ComponentMapper<Pos>
    private lateinit var physMapper: ComponentMapper<Physics>

    override fun process(id: Int) {
        val angle = angleMapper[id]
        val pos = posMapper[id]
        val phys = physMapper[id]

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            // create bullet
            val v = if (phys != null) phys.v.cpy() else Vector2()
            v.add(Vector2(300f, 0f)).rotateRad(angle.value)
            EntityBuilder(world)
                .with(
                    Damage(1),
                    Physics(v),
                    Pos(pos.x, pos.y),
                    Angle(angle.value),
                    Scale(),
                    Renderable(3),
                    //Sprite(game.assets.gems[Random.nextInt(0,6)]),
                    Sprite(game.assets.bullet),
                    Color(game.assets.bg.color)
                )
                .build()
        }
    }
}
