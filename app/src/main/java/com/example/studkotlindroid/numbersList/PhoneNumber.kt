package com.example.studkotlindroid.numbersList

import studkotlindroid.R

data class PhoneNumber(var Name : String, var Number : String){
    val imgId : Int = R.drawable.ic_launcher_foreground
    val firstSymbol : Char
        get() = Name[0].toUpperCase()
}