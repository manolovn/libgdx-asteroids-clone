package dev.manolovn.burger.screen

import com.artemis.World
import com.artemis.WorldConfigurationBuilder
import com.artemis.link.EntityLinkManager
import com.artemis.managers.GroupManager
import com.artemis.managers.TagManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.Board
import dev.manolovn.burger.systems.*
import net.mostlyoriginal.plugin.ProfilerPlugin

class InGameScreen(
        private val game: BurgerMenuGame
) : Screen {

    private lateinit var world: World

    private var board: Board = Board(game.assets)

    override fun show() {

        world = World(WorldConfigurationBuilder()
                .dependsOn(
                        EntityLinkManager::class.java,
                        ProfilerPlugin::class.java
                )
                .with(
                        TagManager(),
                        GroupManager(),
                        // in game logic
                        MatchingSystem(),
                        // input handling
                        MouseSystem(),
                        // rendering
                        CameraSystem(game),
                        RenderingSystem(game),
                )
                .build())
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