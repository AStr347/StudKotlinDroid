package com.example.studkotlindroid.numbersList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NumbersListViewModel : ViewModel() {


    private val _list = MutableStateFlow(PhoneNumberData.getGrouped())
    val list = _list.asStateFlow()

    private val _openAddDialog = MutableStateFlow(false)
    val openAddDialog = _openAddDialog.asStateFlow()

    private val _openEditDialog = MutableStateFlow(false)
    val openEditDialog = _openEditDialog.asStateFlow()

    var editIndex : Int = -1

    companion object{
        private var me : NumbersListViewModel? = null
        val instant : NumbersListViewModel get() {
            if(me == null){
                me = NumbersListViewModel()
            }
            return me!!
        }
    }

    fun onAdd(pn : PhoneNumber){
        val groupedlist = _list.value
        val list : MutableList<PhoneNumber> = (groupedlist.flatMap { it.value }).toMutableList()
        list.add(pn)
        _list.value = list.sortedBy { val s = it.Name; s.toUpperCase() }.groupBy { it.firstSymbol }
        onOpenAddDialog()
    }

    fun onEdit(pn: PhoneNumber){
        val groupedlist = _list.value
        val list : MutableList<PhoneNumber> = (groupedlist.flatMap { it.value }).toMutableList()
        list[editIndex] = pn
        _list.value = list.sortedBy { val s = it.Name; s.toUpperCase() }.groupBy { it.firstSymbol }
        onOpenEditDialog()
    }

    fun onDel(pn: PhoneNumber){
        val groupedlist = _list.value
        val list : MutableList<PhoneNumber> = (groupedlist.flatMap { it.value }).toMutableList()
        list.remove(pn)
        _list.value = list.sortedBy { val s = it.Name; s.toUpperCase() }.groupBy { it.firstSymbol }
        onOpenEditDialog()
    }

    fun onOpenAddDialog(){
        var opend = _openAddDialog.value
        var editopend = _openEditDialog.value
        if(!editopend) {
            opend = !opend
            _openAddDialog.value = opend
        }
    }

    fun onOpenEditDialog(pn: PhoneNumber? = null){
        var opend = _openEditDialog.value
        var addopend = _openAddDialog.value
        if(!addopend) {
            val groupedlist = _list.value
            val list : MutableList<PhoneNumber> = (groupedlist.flatMap { it.value }).toMutableList()
            editIndex = list.indexOf(pn)
            opend = !opend
            _openEditDialog.value = opend
        }
    }
}