package dev.manolovn.burger.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.systems.IteratingSystem
import dev.manolovn.burger.components.Hello

@All(Hello::class)
class HelloWorldSystem : IteratingSystem() {

    protected val mHello: ComponentMapper<Hello>? = null

    override fun process(entityId: Int) {
        println(mHello?.get(entityId)?.message)
    }
}