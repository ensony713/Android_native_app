package com.example.kotlin_test

fun main() {

    // 생성
    val system = mapOf( // 읽기 전용
        "a" to 2,
        "b" to 34
    )
    //system["c"] = 43
    //system["b"] = 4
    // -> 둘 다 오류

    val solarSystem = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )

    // 속성
    println(solarSystem.size)

    solarSystem["Pluto"] = 5
    println(solarSystem.size)

    // 조회
    println(solarSystem["Pluto"])

    // 존재하지 않는 값 조회
    println(solarSystem["Theia"])

    // 삭제
    solarSystem.remove("Pluto")
    println(solarSystem.size)

    solarSystem["Jupiter"] = 78
    println(solarSystem["Jupiter"])
}