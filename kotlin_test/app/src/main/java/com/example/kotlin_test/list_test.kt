package com.example.kotlin_test

fun main() {
    // 생성
    val oldSolarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    // 특성
    println(oldSolarSystem.size)

    // 참조
    println(oldSolarSystem[2])
    println(oldSolarSystem.get(3))

    // index
    println(oldSolarSystem.indexOf("Earth"))
    println(oldSolarSystem.indexOf("Pluto"))

    // for문과 list
    for (planet in oldSolarSystem) {
        println(planet)
    }

    println("===========================")

    // 생성
    val solarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    // 추가
    solarSystem.add("Pluto")

    // 참조
    println(solarSystem[3])
    println(solarSystem[8])

    // 삭제
    solarSystem.remove("Future Moon")

    // contains
    println(solarSystem.contains("Pluto"))
    println("Future Moon" in solarSystem)
}