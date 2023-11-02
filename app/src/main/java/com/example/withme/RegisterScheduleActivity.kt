package com.example.withme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.withme.databinding.ActivityMainPopupBinding
import com.example.withme.databinding.ActivityRegisterScheduleBinding

class RegisterScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}