package com.example.kotlin_test

interface Test{
    fun showing()
    fun viewing() {
        println("Hi, I'm Interface.")
    }
}

class MyClass : Test {
    override fun showing() {
        println("I show my instance.")
    }
    override fun viewing() {

    }
}