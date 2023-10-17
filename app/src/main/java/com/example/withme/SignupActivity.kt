package com.example.withme

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.example.withme.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchSignupBday.setOnCheckedChangeListener{ btnview,isChecked ->
            if (isChecked) {
                DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        binding.tvSignupBday.setText("$year.${month+1}.$dayOfMonth")
                    }
                }, 2000, 6, 1).show()
                binding.tvSignupBday.visibility = View.VISIBLE
            } else {
                binding.tvSignupBday.visibility = View.INVISIBLE
            }
        }


        /*
        binding.chbxSignup.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            }
        }

         */

    }
}