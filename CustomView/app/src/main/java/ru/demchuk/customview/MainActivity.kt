package ru.demchuk.customview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.children
import ru.demchuk.customview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val flexbox = binding.message.children.last() as FlexBoxLayout
        flexbox.children.forEach { child ->
            child.setOnClickListener {
                it.isSelected = !it.isSelected
                val reaction = it as ReactionView
                ++reaction.count
            }
        }
    }
}