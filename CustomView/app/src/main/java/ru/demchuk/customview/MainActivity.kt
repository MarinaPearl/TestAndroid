package ru.demchuk.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.demchuk.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         binding.reactionView.setOnClickListener {
            it.isSelected = !it.isSelected
         }

    }
}