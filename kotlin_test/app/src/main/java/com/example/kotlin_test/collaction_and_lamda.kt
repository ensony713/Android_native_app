package com.example.kotlin_test

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

fun main() {
    cookies.forEach {
        println("Menu item: ${it.name}")
    }

    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }

    println("\n\nFull menu: ")
    fullMenu.forEach {
        println(it)
    }

    // filter는 목록에서 특정 값을 기준으로 필터링한 list를 만들어 줌
    val softBaked = cookies.filter {
        it.softBaked
    }
    println("\n\nSoft cookies: ")
    softBaked.forEach {
        println("${it.name} - $${it.price}")
    }

    // groupBy는 특정 값을 기준으로 값을 나눈 map을 만들어 줌
    val groupedMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    println("\n\nSoft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    // fold는 단일 값을 구하는데 사용됨 = python의 reduce와 동일
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }
    println("\n\nTotal price: $${totalPrice}")

    // 정렬
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
    println("\n\nAlphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }
}