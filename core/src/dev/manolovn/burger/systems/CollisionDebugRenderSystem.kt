package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import dev.manolovn.burger.components.Collision
import dev.manolovn.burger.components.Pos

@All(Collision::class)
class CollisionDebugRenderSystem(
    private val shapeRenderer: ShapeRenderer = ShapeRenderer()
): IteratingSystem() {

    private lateinit var collisionMapper: ComponentMapper<Collision>
    private lateinit var posMapper: ComponentMapper<Pos>

    override fun begin() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    }

    override fun process(entityId: Int) {
        val coll = collisionMapper[entityId]
        val pos = posMapper[entityId]
        shapeRenderer.circle(pos.x, pos.y, coll.radius)
    }

    override fun end() {
        shapeRenderer.end()
    }
}