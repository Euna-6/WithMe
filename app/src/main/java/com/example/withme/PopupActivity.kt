package com.example.withme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.example.withme.databinding.ActivityMainPopupBinding

class PopupActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPopupRegister.setOnClickListener{
            val intent = Intent(this, RegisterScheduleActivity::class.java)
            startActivity(intent)
        }

        binding.btnPopupBack.setOnClickListener{
            finish()
        }

    }
}