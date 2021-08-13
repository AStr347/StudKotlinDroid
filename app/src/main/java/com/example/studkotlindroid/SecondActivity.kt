package com.example.studkotlindroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import studkotlindroid.R
import java.util.*

class SecondActivity : AppCompatActivity() {
    /**
     *  get value from intent
     *  creator must put some data
     */
    private var MaxRandomValue : Int = 1000
    /* something like static property in other languages */
    companion object {
        /* use for intent put(work by name) */
        const val MaxValueRecv : String = "MaxRandomValue"
    }
    private lateinit var rNumber : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        rNumber = findViewById(R.id.rnumber)
        MaxRandomValue = intent.getIntExtra(MaxValueRecv, 1000)
        setRandom()
    }

    private fun setRandom(){
        val random = Random()
        var max = MaxRandomValue
        if(0 > MaxRandomValue){
            max = 100
        }
        rNumber.text = random.nextInt(max + 1).toString()
    }
}