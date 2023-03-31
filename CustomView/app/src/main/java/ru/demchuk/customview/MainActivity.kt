package ru.demchuk.customview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        binding.flexBox.children.forEach {
            it.setOnClickListener { reaction ->
                reaction.isSelected = !reaction.isSelected
                Log.d("aaaaaaaaaaaaaa", "${it.attributeSourceResourceMap[2]}")

            }
        }

    }
}