package com.example.studkotlindroid.trueMVVM

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DataBindedViewModel : ViewModel() {
    private var _user = MutableLiveData(User("Anton Svistov", 21, 0))

    val user : LiveData<User> = _user

    val isAdult : LiveData<String> = Transformations.map(user) {
        if(it.isAdult){
            "Adult"
        } else {
            "Less 18"
        }
    }

    fun onInc(view : View){
        var user = _user.value
        if(user != null) {
            user.age++;
            _user.value = user
        }
    }

    fun onDec(view : View){
        var user = _user.value
        if(user != null) {
            user.age--;
            _user.value = user
        }
    }
}