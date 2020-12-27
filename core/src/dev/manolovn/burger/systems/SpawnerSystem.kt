package dev.manolovn.burger.systems

import com.artemis.annotations.Wire
import com.artemis.utils.EntityBuilder
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.BurgerMenuGame.Companion.H
import dev.manolovn.burger.BurgerMenuGame.Companion.W
import dev.manolovn.burger.components.*
import dev.manolovn.burger.util.Assets
import net.mostlyoriginal.api.system.core.PassiveSystem
import kotlin.random.Random

@Wire
class SpawnerSystem(private val assets: Assets) : PassiveSystem() {

    override fun initialize() {
        super.initialize()

        initBackground()
        initSpaceship()
        initAsteroids()
    }

    private fun initBackground() {
        EntityBuilder(world)
            .with(
                Pos(-W/2f, -H/2f),
                Angle(0f),
                Scale(),
                Renderable(1),
                Sprite(assets.bg),
                Color(),
            )
            .build()
    }

    private fun initSpaceship() {
        EntityBuilder(world)
            .with(
                Ship(),
                Pos(W/2, H/2),
                Angle(MathUtils.PI),
                Scale(),
                Renderable(2),
                Control(),
                Collision(3f),
                Sprite(assets.spaceship),
                Color(),
            )
            .build()
    }

    private fun initAsteroids() {
        for (i in 0..3) {
            val kind = Random.nextInt(assets.gems.size)
            buildAsteroidEntity(kind)
        }
    }

    private fun buildAsteroidEntity(kind: Int) {
        val rand = Random
        EntityBuilder(world)
            .with(
                Pos(W * 0.6f * (rand.nextFloat() - 0.5f),
                    H * (rand.nextFloat() - 0.5f)),
                Renderable(2),
                Physics(Vector2(200f, 0f).rotateRad(MathUtils.PI2 * rand.nextFloat())),
                Angle(MathUtils.PI2 * rand.nextFloat()),
                Sprite(assets.gems[kind]),
                Scale(),
                Collision(3f),
                Color(),
            )
            .build()
    }
}
