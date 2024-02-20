package com.example.kotlin_test

fun main() {

    // 이런 hash를 이용해 집합 구현
    //println("Kotlin".hashCode()) // = -2041707231
    //println("kotlin".hashCode()) // = -1125574399

    val system = setOf("a", "b", "c")
    // 생성
    val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    // 속성
    println(solarSystem.size)

    // 추가
    solarSystem.add("Pluto")
    println(solarSystem.size)

    println(solarSystem.contains("Pluto"))

    solarSystem.add("Pluto")
    println(solarSystem.size)

    // 삭제
    solarSystem.remove("Pluto")
    println(solarSystem.size)
    println(solarSystem.contains("Pluto"))
}