package com.example.cafe.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafe.databinding.ActivityCatalogueBinding

class CatalogueActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCatalogueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityCatalogueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}