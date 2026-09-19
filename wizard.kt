package com.example.test1


class Wizard(
    var name: String
) {
    var maxHp =50
    var hp =50

    var maxMana= 30
    var mana= 30

    var kills= 0

    var manaPotions =5
    var healthPotions= 5

    var isStrong =false
    var lifesteal = 0



    fun showStats() {
        println()
        println("========== $name'S STATS ==========")
        println("HP: $hp / $maxHp")
        println("Mana: $mana / $maxMana")
        println("Kills needed to evolve: $kills / 5")
        println("Mana Potions held: $manaPotions")
        println("Health Potions held: $healthPotions")

        if (isStrong) {
            println("Lifesteal: $lifesteal")
        }

        println("======================")
    }

    fun rename(newName: String) {
        name = newName
    }
    fun drinkHealthPotion() {
        if (healthPotions <= 0) {
            println("You have no health potions!")
            return
        }
        healthPotions--
        hp += 25

        if (hp > maxHp) {
            hp = maxHp
        }

        println("You restored 25 HP!")
    }
    fun drinkManaPotion() {
        if (manaPotions <= 0) {
            println("You have no mana potions!")
            return
        }

        manaPotions--

        mana += 15

        if (mana >maxMana) {
            mana =maxMana
        }

        println("You restored 15 Mana!")
    }
}