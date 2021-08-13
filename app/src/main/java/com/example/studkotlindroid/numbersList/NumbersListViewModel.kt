package com.example.studkotlindroid.numbersList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NumbersListViewModel : ViewModel() {
    private val _list = MutableStateFlow(PhoneNumberData.getGrouped())
    val list = _list.asStateFlow()
}