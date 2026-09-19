class OrderSystem {

    private val menu=mutableListOf<MenuItem>()
    private val orders=mutableListOf<Order>()

    fun addMenu(){

        println()
        println("========== ADD MENU ==========")

        print("Enter food name: ")
        val name = readln()

        if (name.isBlank()) {
            println("Food name cannot be empty!")
            return
        }

        print("Enter description: ")
        val description = readln()

        if (description.isBlank()) {
            println("Description cannot be empty!")
            return
        }

        print("Enter price: ")
        val price = readln().toDoubleOrNull()

        if (price == null || price <= 0) {
            println("Invalid price!")
            return
        }

        val newItem = MenuItem(
            name,
            description,
            price
        )

        menu.add(newItem)

        println("Menu item added successfully!")
    }
    fun editMenu(){


        if (menu.isEmpty()) {
            println("There are no menu items to edit.")
            return
        }

        viewMenu()

        print("Choose menu item to edit: ")
        val choice = readln().toIntOrNull()

        if (choice == null || choice !in 1..menu.size) {
            println("Invalid menu choice!")
            return
        }

        val item = menu[choice - 1]

        print("Enter new name: ")
        val newName = readln()

        if (newName.isBlank()) {
            println("Name cannot be empty!")
            return
        }

        print("Enter new description: ")
        val newDescription = readln()

        if (newDescription.isBlank()) {
            println("Description cannot be empty!")
            return
        }

        print("Enter new price: ")
        val newPrice = readln().toDoubleOrNull()

        if (newPrice == null || newPrice <= 0) {
            println("Invalid price!")
            return
        }

        item.name = newName
        item.description = newDescription
        item.price = newPrice

        println("Menu item updated successfully!")

    }

    fun deleteMenu(){

        if (menu.isEmpty()) {
            println("There are no menu items to delete.")
            return
        }

        viewMenu()

        print("Choose menu item to delete: ")
        val choice = readln().toIntOrNull()

        if (choice == null || choice !in 1..menu.size) {
            println("Invalid menu choice!")
            return
        }

        val removedItem = menu.removeAt(choice - 1)

        println("${removedItem.name} has been deleted.")
    }
    fun viewMenu(){

        if (menu.isEmpty()) {
            println("There are no menu items.")
            return
        }

        println()
        println("========== MENU ==========")

        for (i in menu.indices) {

            val item = menu[i]

            println("${i + 1}. ${item.name}")
            println("   Description: ${item.description}")
            println("   Price: $${item.price}")
            println()
        }
    }
    fun makeOrder(){

        if (menu.isEmpty()) {
            println("There are no menu items available.")
            return
        }

        println()
        println("========== MAKE ORDER ==========")

        print("Enter customer name: ")
        val customerName = readln()

        if (customerName.isBlank()) {
            println("Customer name cannot be empty!")
            return
        }

        val order = Order(customerName)

        while (true) {

            viewMenu()

            print("Choose food (0 to finish): ")
            val choice = readln().toIntOrNull()

            if (choice == null) {
                println("Please enter a number!")
                continue
            }

            if (choice == 0) {
                break
            }

            if (choice !in 1..menu.size) {
                println("Invalid menu choice!")
                continue
            }

            val selectedItem = menu[choice - 1]

            print("Enter quantity: ")
            val quantity = readln().toIntOrNull()

            if (quantity == null || quantity <= 0) {
                println("Quantity must be greater than 0!")
                continue
            }

            val orderItem = OrderItem(
                selectedItem,
                quantity
            )

            order.items.add(orderItem)

            println("${selectedItem.name} x$quantity added!")
        }

        if (order.items.isEmpty()) {
            println("No items were ordered.")
            return
        }

        orders.add(order)

        println()
        println("========== ORDER CREATED ==========")
        println("${order.customername}'s ORDER")

        for (item in order.items) {
            println(
                "${item.menuItem.name} x${item.quantity} " +
                        "$${item.getsubtotal()}"
            )
        }

        println("-----------------------------------")
        println("TOTAL: $${order.gettotal()}")
    }
    fun viewOrders(){

        if (orders.isEmpty()) {
            println("There are no orders yet.")
            return
        }

        println()
        println("========== ALL ORDERS ==========")

        for (order in orders) {

            println()
            println("${order.customername}'s ORDER")

            for (item in order.items) {

                println(
                    "${item.menuItem.name} x${item.quantity} " +
                            "$${item.getsubtotal()}"
                )
            }

            println("--------------------------------")
            println("TOTAL: $${order.gettotal()}")
        }
    }

    fun run() {

        while (true) {

            println()
            println("========== ORDER SYSTEM ==========")
            println("1. Make Order")
            println("2. View Orders")
            println("3. View Menu")
            println("4. Add Menu")
            println("5. Edit Menu")
            println("6. Delete Menu")
            println("7. Exit")

            print("Choose: ")

            val choice = readln().toIntOrNull()

            when (choice) {
                1 -> makeOrder()
                2 -> viewOrders()
                3 -> viewMenu()
                4 -> addMenu()
                5 -> editMenu()
                6 -> deleteMenu()

                7 -> {
                    println("Thank you!")
                    break
                }

                else -> {
                    println("Invalid choice!")
                }
            }
        }
    }


}