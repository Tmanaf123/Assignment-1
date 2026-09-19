class Order (
    var customername: String
){


    val items=mutableListOf<OrderItem>()

    fun gettotal(): Double {

        var total=0.0;

        for (item in items){

            total += item.getsubtotal()
        }

        return total
    }
}