package com.example.withme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.withme.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 로그인 버튼
        binding.btnLogin.setOnClickListener {
            /*
            if문
            로그인
             */
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼
        binding.tvSignup.setOnClickListener{
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}