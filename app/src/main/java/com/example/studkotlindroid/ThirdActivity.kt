package com.example.studkotlindroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import studkotlindroid.R

class ThirdActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            textView.text = editText.text
        }
    }

    /**
     * save string into Bundle
     * and get that string after Restore
     */
    companion object{
        const val saveString : String = "saveString"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(saveString, textView.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        textView.text = savedInstanceState.getString(saveString)
    }
}