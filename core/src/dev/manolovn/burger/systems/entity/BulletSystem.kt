package dev.manolovn.burger.systems.entity

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.managers.GroupManager
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import dev.manolovn.burger.components.*
import dev.manolovn.burger.systems.entity.SpawnerSystem.Group.BULLET
import dev.manolovn.burger.systems.render.AssetsSystem
import dev.manolovn.burger.util.EntityFactory

@All(Ship::class)
class BulletSystem : IteratingSystem() {

    private lateinit var assets: AssetsSystem
    private lateinit var groupManager: GroupManager

    private lateinit var angleMapper: ComponentMapper<Angle>
    private lateinit var posMapper: ComponentMapper<Pos>

    override fun process(id: Int) {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            val angle = angleMapper[id]
            val pos = posMapper[id]
            val bullet = EntityFactory.bullet(world, pos, angle, assets.bullet)
            groupManager.add(bullet, BULLET)
        }
    }
}
