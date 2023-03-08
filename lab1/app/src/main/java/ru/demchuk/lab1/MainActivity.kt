package ru.demchuk.lab1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val messageInTextView = findViewById<TextView>(R.id.message)
        val intent = intent
        println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb${intent.getStringExtra("message")}")
        messageInTextView.text = intent.getStringExtra("message")
    }

    fun onClickStart(view: View) {
        val intent = Intent(this, WorkerWithServer::class.java)
        startActivity(intent)
    }
}