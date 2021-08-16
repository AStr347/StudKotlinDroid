package com.example.studkotlindroid.trueMVVM

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import studkotlindroid.R
import studkotlindroid.databinding.ActivityDataBindedBinding

class DataBinded : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain ViewModel from ViewModelProviders
        val viewModel = ViewModelProviders.of(this).get(DataBindedViewModel::class.java)
        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        // val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)
        // Obtain binding
        val binding : ActivityDataBindedBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binded)
        // Bind layout with ViewModel
        binding.viewmodel = viewModel
        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this
    }
}