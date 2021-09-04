package me.xiaro.mtfsim

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.clear
import kotlinx.html.div
import kotlinx.html.dom.append
import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import me.xiaro.mtfsim.contents.initEvents
import me.xiaro.mtfsim.event.EventManager
import org.w3c.dom.Node

private lateinit var simulation: Simulation

fun main() {
    initEvents()
    console.info("${EventManager.events.size} events initialized")
    println(EventManager.events)
    println(EventManager.group)

    simulation = newSimulation()

    window.onclick = {
        try {
            document.body?.grow()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun newSimulation(): Simulation {
    return Simulation(
        AttributeMap().apply {
            set(Attribute.BEAUTY, 20)
            set(Attribute.IQ, 20)
            set(Attribute.STRENGTH, 20)
            set(Attribute.ECONOMIC, 20)

            set(Attribute.HAPPINESS, 20)
            set(Attribute.HEALTH, 20)
            set(Attribute.FEMININITY, 0)
        }
    )
}

fun Node.grow() {
    println("Clicked!")

    if (simulation.dead) {
        println("Restarted!")
        document.body?.clear()
        simulation = newSimulation()
    }

    val result = simulation.grow()

    append {
        div {
            +"${simulation.age}岁: ${result.message}"
        }
    }

    println(simulation.attributes)
}