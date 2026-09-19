package com.example.test1


class WizardSystem {

    private lateinit var wizard: Wizard

    fun run() {
        print("Name plz? ")
        val name = readln()

        wizard = Wizard(name)
        println("k gl, ${wizard.name}! You're gonna need it!")

        while (true) {
            println()
            println("What r you going to do?")
            println("1. View Stats")
            println("2. Enter battle")
            println("3. Exit")
            print("Choose: ")
            when (readln()) {
                "1" -> viewStats()
                "2" -> battle()
                "3" -> {
                    println("Goodbye!!")
                    break
                }
                else -> println("Invalid choice!")
            }
        }
    }

    private fun viewStats() {
        wizard.showStats()
    }

    private fun battle() {

        val enemyElement = Element.values().random()
        val enemy = Enemy(enemyElement)

        println()
        println("========== BATTLE ==========")

        while (wizard.hp > 0 && enemy.hp > 0) {

            println()
            println(wizard.name)
            println("HP: ${wizard.hp}/ ${wizard.maxHp}")
            println("Mana: ${wizard.mana}/ ${wizard.maxMana}")
            println("HP Potions: ${wizard.healthPotions}")
            println("MP Potions: ${wizard.manaPotions}")
            println()

            enemy.showStats()
            println()
            println("------------------------")
            println("a. Fire Attack")
            println("b. Water Attack")
            println("c. Grass Attack")
            println("d. Drink potion")
            println("e. Run")

            print("Choose: ")

            when (readln().lowercase()) {

                "a"-> {
                    if (wizard.mana < 10) {
                        println("Not enough mana!")
                        continue
                    }
                    wizard.mana -= 10

                    var damage = 10

                    if (enemy.element==Element.GRASS) {
                        damage *= 2
                        println("It's super effective!")
                    }

                    if (wizard.isStrong) {
                        damage = (damage * 1.5).toInt()
                    }

                    enemy.hp-=damage
                    if (enemy.hp < 0) {
                        enemy.hp = 0
                    }

                    println("You dealt $damage damage!")
                    if (wizard.isStrong) {

                        wizard.hp +=wizard.lifesteal

                        if (wizard.hp> wizard.maxHp) {
                            wizard.hp= wizard.maxHp
                        }

                        println("Lifesteal restored ${wizard.lifesteal} HP!")
                    }
                }
                "b" -> {

                    if (wizard.mana < 10) {
                        println("Not enough mana!")
                        continue
                    }

                    wizard.mana-=10

                    var damage=10

                    if (enemy.element == Element.FIRE) {
                        damage *= 2
                        println("It's super effective!")
                    }

                    if (wizard.isStrong) {
                        damage = (damage * 1.5).toInt()
                    }

                    enemy.hp-=damage

                    if (enemy.hp<0) {
                        enemy.hp=0
                    }
                    println("You dealt $damage damage!")
                    if (wizard.isStrong) {

                        wizard.hp += wizard.lifesteal

                        if (wizard.hp >wizard.maxHp) {
                            wizard.hp =wizard.maxHp
                        }

                        println("Lifesteal restored ${wizard.lifesteal} HP!")
                    }
                }
                "c" -> {
                    if (wizard.mana<10) {
                        println("Not enough mana!")
                        continue
                    }

                    wizard.mana-=10

                    var damage=10
                    if (enemy.element==Element.WATER) {
                        damage *= 2
                        println("It's super effective!")
                    }

                    if (wizard.isStrong) {
                        damage = (damage * 1.5).toInt()
                    }

                    enemy.hp-=damage

                    if (enemy.hp<0) {
                        enemy.hp=0
                    }
                    println("You dealt $damage damage!")

                    if (wizard.isStrong) {

                        wizard.hp+=wizard.lifesteal

                        if (wizard.hp >wizard.maxHp) {
                            wizard.hp=wizard.maxHp
                        }

                        println("Lifesteal restored ${wizard.lifesteal} HP!")
                    }
                }

                "d" -> {

                    println()
                    println("1. Health Potion")
                    println("2. Mana Potion")
                    println("3. Back")

                    print("Choose: ")

                    when (readln()) {
                        "1" -> wizard.drinkHealthPotion()
                        "2" -> wizard.drinkManaPotion()

                        "3" -> continue

                        else -> println("Invalid choice!")
                    }
                }
                "e" -> {
                    println("You ran away!")
                    return
                }
                else -> {
                    println("Invalid choice!")
                    continue
                }
            }
            if (enemy.hp>0) {

                wizard.hp-=10

                if (wizard.hp<0) {
                    wizard.hp=0
                }

                println("${enemy.name} dealt 10 damage!")
            }
        }

        if (wizard.hp<=0) {
            println()
            println("${wizard.name} died!")
            println("Starting again...")

            wizard = Wizard(wizard.name)
            return
        }

        if (enemy.hp <=0) {

            println()
            println("You defeated ${enemy.name}!")

            wizard.kills++

            println("Kills: ${wizard.kills}/5")
            if (wizard.kills == 5 && !wizard.isStrong) {
                evolvewizard()
            } else if (wizard.isStrong) {
                wizard.lifesteal++
            }
        }
    }

    private fun evolvewizard() {

        println()
        println("==============================")
        println("  STRONG WIZARD!")
        println("==============================")

        wizard.isStrong=true

        wizard.maxHp=(wizard.maxHp*1.5).toInt()
        wizard.hp=wizard.maxHp

        wizard.maxMana=(wizard.maxMana*1.5).toInt()
        wizard.mana=wizard.maxMana

        wizard.lifesteal =1


        println("${wizard.name} has evolved!")
        println("Max HP: ${wizard.maxHp}")
        println("Max Mana: ${wizard.maxMana}")
        println("Lifesteal: ${wizard.lifesteal}")
    }
}