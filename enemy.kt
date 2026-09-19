package com.example.test1


enum class Element {
    FIRE,
    WATER,
    GRASS
}
class Enemy(
    val element: Element
) {
    val name = element.name + "mon"
    var maxHp = 30
    var hp = maxHp
    fun showStats() {
        println("$name")
        println("HP: $hp / $maxHp")
        println("Type: $element")
    }
}