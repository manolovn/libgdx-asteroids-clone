package dev.manolovn.burger.systems

import com.artemis.annotations.Wire
import com.artemis.utils.EntityBuilder
import dev.manolovn.burger.BurgerMenuGame
import dev.manolovn.burger.components.Pos
import net.mostlyoriginal.api.component.graphics.Render
import net.mostlyoriginal.api.component.graphics.SpriteAsset
import net.mostlyoriginal.api.system.core.PassiveSystem

@Wire
class BoardSpawnerSystem(private val game: BurgerMenuGame) : PassiveSystem() {

    override fun initialize() {
        super.initialize()

        initBackground()
    }

    private fun initBackground() {
        val spriteAsset = SpriteAsset()
        spriteAsset.asset = game.assets.bg
        EntityBuilder(world)
                .with(
                        Pos(0, 0)
                )
                .build()
                .edit()
                .add(
                        Render(100)
                )
    }
}