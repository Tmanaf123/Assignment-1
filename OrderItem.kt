class OrderItem(

    val menuItem: MenuItem,
    var quantity: Int
) {

    fun getsubtotal(): Double{
        return menuItem.price*quantity
    }
}