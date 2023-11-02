package com.example.withme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.withme.databinding.ActivityListFriendsBinding
import com.example.withme.databinding.ActivityMainBinding

class ListFriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iv_list_star = findViewById<ImageView>(R.id.iv_list_star)
        iv_list_star.setOnClickListener{
            iv_list_star.setImageResource(R.drawable.star_fill_yellow)
        }

    }
}