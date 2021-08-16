package com.example.studkotlindroid.trueMVVM


class User(val name : String, var age : Int, var likes : Int){
    val isAdult : Boolean
        get() = age >= 18
}
