package com.example.myapplication.models

class Horse(val name: String?,
            val age: String?,
            val mother: String?,
            val father: String?,
            val location: String?) {

    private lateinit var horses: List<Horse>

    fun initializeData(): List<Horse>{
        horses = listOf(Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада"),
            Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада"),
            Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада"))
        return horses
    }

}