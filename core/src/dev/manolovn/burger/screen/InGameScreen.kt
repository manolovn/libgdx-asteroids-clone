package dev.manolovn.burger.screen

import com.artemis.World
import com.artemis.WorldConfigurationBuilder
import com.artemis.link.EntityLinkManager
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import dev.manolovn.burger.AsteroidsGame
import dev.manolovn.burger.systems.collide.AsteroidCollisionSystem
import dev.manolovn.burger.systems.collide.BulletCollisionSystem
import dev.manolovn.burger.systems.collide.CollisionDebugRenderSystem
import dev.manolovn.burger.systems.collide.CollisionSystem
import dev.manolovn.burger.systems.entity.PowerUpSpawnerSystem
import dev.manolovn.burger.systems.entity.SpawnerSystem
import dev.manolovn.burger.systems.input.KeyboardSystem
import dev.manolovn.burger.systems.input.TouchSystem
import dev.manolovn.burger.systems.logic.GameSystem
import dev.manolovn.burger.systems.render.*
import net.mostlyoriginal.api.plugin.extendedcomponentmapper.ExtendedComponentMapperPlugin
import net.mostlyoriginal.plugin.ProfilerPlugin

class InGameScreen(
    private val game: AsteroidsGame
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
                    AssetsSystem(),
                    SpawnerSystem(),
                    PowerUpSpawnerSystem(),
                    // in game logic
                    PhysicsSystem(),
                    CollisionSystem(),
                    BulletCollisionSystem(),
                    AsteroidCollisionSystem(),
                    // input handling
                    KeyboardSystem(),
                    TouchSystem(),
                    // rendering

                    CameraSystem(game.batch),
                    CameraShakeSystem(),
                    MapRenderingSystem(game.batch),
                    RenderingSystem(game.batch),
                    AnimRenderingSystem(game.batch),
                    //CollisionDebugRenderSystem(),
                    ShaderRenderingSystem(game.batch),
                    GameSystem(game, game.batch),
                )
                .build()
        )
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

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