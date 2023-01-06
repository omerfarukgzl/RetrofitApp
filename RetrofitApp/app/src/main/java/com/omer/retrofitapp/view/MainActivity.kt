package com.omer.retrofitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omer.retrofitapp.R
import com.omer.retrofitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, PostListFragment()).commit()
    }
}
