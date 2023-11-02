package com.example.withme

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.example.withme.databinding.ActivityMainPopupBinding

class PopupActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // 타이틀바 없애기

        val binding = ActivityMainPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPopupRegister.setOnClickListener{

        }

        binding.btnPopupBack.setOnClickListener{
            finish()
        }

    }
}