package dev.manolovn.burger.screen

import com.artemis.World
import com.artemis.WorldConfigurationBuilder
import com.artemis.link.EntityLinkManager
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.badlogic.gdx.Screen
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.systems.*
import net.mostlyoriginal.api.plugin.extendedcomponentmapper.ExtendedComponentMapperPlugin
import net.mostlyoriginal.plugin.ProfilerPlugin

class InGameScreen(
    private val game: BurgerMenuGame
) : Screen {

    private lateinit var world: World

    override fun show() {
        world = World(
            WorldConfigurationBuilder()
                .dependsOn(
                    EntityLinkManager::class.java,
                    ProfilerPlugin::class.java,
                    ExtendedComponentMapperPlugin::class.java
                )
                .with(
                    TagManager(),
                    GroupManager(),
                    SpawnerSystem(game.assets),
                    // in game logic
                    BulletSystem(game.assets),
                    PhysicsSystem(),
                    CollisionSystem(),
                    // input handling
                    MouseSystem(),
                    KeyboardSystem(),
                    // rendering
                    CameraSystem(game),
                    RenderingSystem(game.batch),
                    CollisionDebugRenderSystem()
                )
                .build()
        )
    }

    override fun render(delta: Float) {
        world.delta = delta
        world.process()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        game.dispose()
    }
}