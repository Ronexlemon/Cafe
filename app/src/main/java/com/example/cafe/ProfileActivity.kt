package com.example.cafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafe.databinding.ActivityUpload2Binding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpload2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpload2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}