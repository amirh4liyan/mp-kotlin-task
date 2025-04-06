package edu.sharif.kotlin


fun main(){
    var INPUT: String
    var CODE: Int
    while (true) {
        println("\tSelect Option from Menu: ")
        println("\t\t1) Get Data From Server Based on Username")
        println("\t\t2) Show Available Users")
        println("\t\t3) Search Based on Username")
        println("\t\t4) Search Based on Repository Name")
        println("\t\t5) Exit App")
        print("[In-Int]: Enter Item Code (1 -> 5): ")
        INPUT = readln()
        CODE = -1
        try {
            CODE = INPUT.toInt()
        } catch (e: NumberFormatException) {
            println("[Err]: Invalid Input")
        }

        when (CODE) {
            1 -> print("1")
            2 -> print("2")
            3 -> print("3")
            4 -> print("4")
            5 -> return
            else -> println("[Out]: Invalid Input")
        }
    }
}