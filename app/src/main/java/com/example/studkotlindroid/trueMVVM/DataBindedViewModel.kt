package com.example.studkotlindroid.trueMVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DataBindedViewModel : ViewModel() {
    private var _user = MutableLiveData(User("Anton Svistov", 21, 0))

    val user : LiveData<User> = _user

    val popular : LiveData<Popular> = Transformations.map(user) {
        when {
            it.likes > 9 -> Popular.STAR
            it.likes > 4 -> Popular.POPULAR
            else -> Popular.NORMAL
        }
    }

    val isAdult : LiveData<String> = Transformations.map(user) {
        if(it.isAdult){
            "Adult"
        } else {
            "Near 18"
        }
    }

    fun onLike(){
        val value = _user.value
        if(value != null){
            value.likes++
            _user.value = value
        }
    }
}