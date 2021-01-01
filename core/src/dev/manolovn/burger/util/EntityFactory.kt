package dev.manolovn.burger.util

import com.artemis.Entity
import com.artemis.World
import com.artemis.utils.EntityBuilder
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.AsteroidsGame.Companion.H
import dev.manolovn.burger.AsteroidsGame.Companion.W
import dev.manolovn.burger.components.*
import kotlin.random.Random
import com.badlogic.gdx.graphics.g2d.Sprite as GdxSprite

object EntityFactory {

    fun asteroid(world: World, sprites: MutableList<GdxSprite>): Entity {
        val rand = Random
        val kind = Random.nextInt(sprites.size)
        val x = Gdx.graphics.width * 0.6f * (rand.nextFloat() - 0.5f)
        val y = Gdx.graphics.height * (rand.nextFloat() - 0.5f)
        return EntityBuilder(world)
            .with(
                Pos(
                    x + (sprites[kind].width / 2),
                    y + (sprites[kind].height / 2)
                ),
                Renderable(2),
                Physics(Vector2(300f, 0f).rotateRad(MathUtils.PI2 * rand.nextFloat())),
                Angle(),
                Sprite(sprites[kind]),
                Scale(),
                Collision(10f),
                Color(),
            )
            .build()
    }

    fun ship(world: World, sprite: GdxSprite): Entity = EntityBuilder(world)
        .with(
            Ship(),
            Pos(W / 2, H / 2),
            Angle(),
            Scale(),
            Renderable(2),
            Control(),
            Collision(10f),
            Sprite(sprite),
            Color(),
        )
        .build()

    fun bg(world: World, sprite: GdxSprite): Entity =
        EntityBuilder(world)
            .with(
                MapElement(),
                Pos(0f, 0f),
                Angle(0f),
                Scale(),
                Renderable(1),
                Sprite(sprite),
                Color(),
            )
            .build()

    fun bullet(world: World, pos: Pos, angle: Angle, sprite: GdxSprite): Entity =
        EntityBuilder(world)
            .with(
                Damage(1),
                Physics(Vector2(600f, 0f).rotateRad(angle.value)),
                Pos(pos.x, pos.y),
                Angle(angle.value),
                Scale(),
                Collision(3f),
                Renderable(3),
                Sprite(sprite),
                Color(),
            )
            .build()

    fun explosion(world: World, pos: Pos): Entity =
        EntityBuilder(world)
            .with(pos, Anim(id = "explosion"))
            .build()
}
