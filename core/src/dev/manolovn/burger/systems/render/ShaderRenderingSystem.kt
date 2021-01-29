package dev.manolovn.burger.systems.render

import com.artemis.ComponentMapper
import com.artemis.EntitySystem
import com.artemis.annotations.All
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Vector2
import dev.manolovn.burger.components.Pos
import dev.manolovn.burger.components.Shader

@All(Shader::class)
class ShaderRenderingSystem(
    private val batch: SpriteBatch,
) : EntitySystem() {

    private lateinit var shaderMapper: ComponentMapper<Shader>
    private lateinit var posMapper: ComponentMapper<Pos>

    private val fbo: FrameBuffer =
        FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.width, Gdx.graphics.height, true)
    private var time = 0f

    override fun begin() {
        batch.begin()
    }

    override fun processSystem() {
        entities.forEach {
            val shader = shaderMapper[it]
            val pos = posMapper[it]
            val vertexShader = Gdx.files.internal(shader.vertex).readString()
            val fragmentShader = Gdx.files.internal(shader.fragment).readString()
            val shaderProgram = ShaderProgram(vertexShader, fragmentShader)
            ShaderProgram.pedantic = false
            renderShader(shaderProgram, pos)
            time += world.delta
        }
    }

    private fun renderShader(shader: ShaderProgram, pos: Pos) {
        batch.end()
        batch.flush()
        fbo.begin()
        batch.begin()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        // draw everything
        batch.end()
        batch.flush()
        fbo.end()
        batch.begin()
        batch.shader = shader

        val v = Vector2(pos.x, pos.y)
        v.x = v.x / Gdx.graphics.width
        v.y = v.y / Gdx.graphics.height
        shader.setUniformf("time", time)
        shader.setUniformf("center", v)
        val texture = fbo.colorBufferTexture
        val textureRegion = TextureRegion(texture)
        // and.... FLIP!  V (vertical) only
        textureRegion.flip(false, true)
        batch.draw(textureRegion, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.shader = null
    }

    override fun end() {
        batch.end()
    }
}
