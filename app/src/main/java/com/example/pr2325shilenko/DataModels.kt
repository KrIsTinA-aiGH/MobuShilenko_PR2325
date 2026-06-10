package com.example.pr2325shilenko

import androidx.compose.ui.graphics.Color

data class Analysis(
    val id: Int,
    val name: String,
    val days: String,
    val price: Int,
    val category: String
)

data class Promo(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val backgroundColor: Color
)

object SampleData {
    val analyses = listOf(
        Analysis(1, "Тироксин свободный (Т4 свободный)", "1 день", 680, "Популярные"),
        Analysis(2, "Группа крови + Резус-фактор", "1 день", 750, "Популярные"),
        Analysis(3, "Общий анализ крови", "1 день", 500, "Популярные"),
        Analysis(4, "Глюкоза крови", "1 день", 300, "Популярные"),
        Analysis(5, "Холестерин общий", "1 день", 400, "Популярные"),
        Analysis(6, "Антитела к SARS-CoV-2", "2 дня", 1500, "Covid"),
        Analysis(7, "ПЦР тест на COVID-19", "1 день", 2000, "Covid"),
        Analysis(8, "Комплексное обследование перед вакцинацией", "3 дня", 4000, "Covid"),
        Analysis(9, "Биохимический анализ крови", "2 дня", 1200, "Комплексные"),
        Analysis(10, "Гормоны щитовидной железы", "3 дня", 1800, "Комплексные"),
        Analysis(11, "Витамин D", "2 дня", 1600, "Комплексные"),
        Analysis(12, "Ферритин", "1 день", 900, "Комплексные")
    )

    val promos = listOf(
        Promo(1, "Подготовка к вакцинации", "Комплексное обследование перед вакцинацией", 4000, Color(0xFF4DD0E1)),
        Promo(2, "Check-up для женщин", "Полное обследование здоровья", 5500, Color(0xFF4DD0E1)),
        Promo(3, "Скидка 20% на анализы", "При заказе от 3000 рублей", 2500, Color(0xFF4DD0E1)),
        Promo(4, "Чек-ап для мужчин", "9 исследований", 8000, Color(0xFF90CAF9))
    )
}