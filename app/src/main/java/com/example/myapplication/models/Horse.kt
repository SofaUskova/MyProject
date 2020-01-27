package com.example.myapplication.models

class Horse(val name: String?,
            val age: String?,
            val mother: String?,
            val father: String?,
            val location: String?,
            val color: String?,
            val price: String?) {

    private lateinit var horses: List<Horse>

    fun initializeData(): List<Horse>{
        horses = listOf(Horse("Клюква", "2010г.р.", " - Балерина", "Карнавал", "КСК \"Отрада\"", "соловая", "250 000р."),
            Horse("Карабаин", "2011г.р.", " - Бусинка", "Крампус", "КСК \"Свободный ветер\"", "серый", "70 000р."),
            Horse("Клюква", "2010г.р.", " - Балерина", "Карнавал", "КСК \"Отрада\"", "соловая", "250 000р."),
            Horse("Карабаин", "2011г.р.", " - Бусинка", "Крампус", "КСК \"Свободный ветер\"", "серый", "70 000р."),
            Horse("Клюква", "2010г.р.", " - Балерина", "Карнавал", "КСК \"Отрада\"", "соловая", "250 000р."),
            Horse("Карабаин", "2011г.р.", " - Бусинка", "Крампус", "КСК \"Свободный ветер\"", "серый", "70 000р."))
        return horses
    }

}