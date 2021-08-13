package com.example.studkotlindroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.studkotlindroid.ComposeUi.ComposedUIActivity
import com.example.studkotlindroid.numbersList.NumbersListComposed
import com.example.studkotlindroid.trueMVVM.DataBinded
import studkotlindroid.R

class MainActivity : AppCompatActivity() {
    private var count : Int = 0
    private lateinit var hw : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hw = findViewById(R.id.hw)
    }

    /* inc count and show in TextView */
    fun onCount(view : View){
        hw.text = (++count).toString()
    }
    /* show msg */
    fun onToast(view: View) = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show()
    /* start new activity */
    fun onRandom(view: View) {
        var randomIntent = Intent(this, SecondActivity::class.java)
        randomIntent.putExtra(SecondActivity.MaxValueRecv, count)
        startActivity(randomIntent)
    }
    fun onSave(view: View) = startActivity(Intent(this, ThirdActivity::class.java))

    fun onMvvm(view: View) = startActivity(Intent(this, DataBinded::class.java))

    fun onCompose(view: View) = startActivity(Intent(this, ComposedUIActivity::class.java))

    fun onList(view: View) = startActivity(Intent(this, NumbersListComposed::class.java))
}