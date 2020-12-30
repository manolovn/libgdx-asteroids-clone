package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IteratingSystem
import com.artemis.utils.EntityBuilder
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.components.*
import dev.manolovn.burger.util.Assets

@All(Ship::class)
class BulletSystem(private val assets: Assets) : IteratingSystem() {

    private lateinit var groupManager: GroupManager

    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var posMapper: ComponentMapper<Pos>

    override fun process(id: Int) {
        val angle = angleMapper[id]
        val pos = posMapper[id]

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            // create bullet
            val bullet = EntityBuilder(world)
                .with(
                    Damage(1),
                    Physics(Vector2(300f, 0f).rotateRad(angle.value)),
                    Pos(pos.x, pos.y),
                    Angle(angle.value),
                    Scale(),
                    Collision(3f),
                    Renderable(3),
                    Sprite(assets.bullet),
                    Color(),
                )
                .build()
            groupManager.add(bullet,"bullet")
        }
    }
}
