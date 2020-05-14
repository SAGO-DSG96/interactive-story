package com.comercializadorasago.interactivestory.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.comercializadorasago.interactivestory.R

class MainActivity : AppCompatActivity() {

    private lateinit var nameField: EditText
    private lateinit var startBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameField  = findViewById(R.id.nameEditText)
        startBtn = findViewById(R.id.startButton)
        startBtn.setOnClickListener {
            val name:String = nameField.text.toString()
            StarStory(name)
        }


    }

    override fun onResume() {
        super.onResume()
        nameField.setText("")
    }

    private fun StarStory(name:String) {
        val intent = Intent(this, StoryActivity::class.java)
        val key:String = resources.getString(R.string.key_name)
        intent.putExtra(key, name)
        startActivity(intent)
    }
}
