package com.example.myapplication.models

class Horse(
    val name: String?,
    val age: String?,
    val mother: String?,
    val father: String?,
    val location: String?,
    val price: Int,
    var favorite: Boolean = false
) {

    companion object {

        fun initializeData(): MutableList<Horse> {
            return mutableListOf(
                Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада", 250000),
                Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада", 50000),
                Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада", 150000)
            )
        }

    }
}