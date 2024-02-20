package com.example.kotlin_test

fun main() {

    val coins: (Int) -> String = {
        "$it quarters"
    }

    val cupcake: (Int) -> String = {
        "Have a cupcake!"
    }

    val treatFunction = trickOrTreat(false, coins)
    val trickFunction = trickOrTreat(true, null)
    //val treatFunction2 = trickOrTreat(false) { "$it quarters" }

    repeat(4) {
        treatFunction()
    }
    trickFunction()
}

val trick = {
    println("No treats!")
}
val treat = {
    println("Have a treat!")
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    return if (isTrick) {
        trick
    } else {
        if (extraTreat != null){ println(extraTreat(5)) }
        treat
    }
}

