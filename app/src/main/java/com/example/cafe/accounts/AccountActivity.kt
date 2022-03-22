package com.example.cafe.accounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafe.R
import com.example.cafe.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}