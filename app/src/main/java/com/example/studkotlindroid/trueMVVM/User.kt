package com.example.studkotlindroid.trueMVVM


class User(val name : String, val age : Int, var likes : Int){
    val isAdult : Boolean
        get() = age >= 18
}

enum class Popular {
    NORMAL,
    POPULAR,
    STAR
}