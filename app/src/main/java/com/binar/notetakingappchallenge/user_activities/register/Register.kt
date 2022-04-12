package com.binar.notetakingappchallenge.user_activities.register

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binar.notetakingappchallenge.databinding.RegisterBinding
import com.binar.notetakingappchallenge.user_activities.login.Login


class Register : AppCompatActivity() {
    private lateinit var binding: RegisterBinding
    private val sharedPrefFile = "userdata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)


        binding.btnRegister.setOnClickListener {
            val usernameValue = binding.etUsernameRegister.text.toString()
            val emailValue = binding.etEmailRegister.text.toString()
            val passwordValue = binding.etPasswordRegister.text.toString()
            val confirmPasswordValue = binding.etConfirmPasswordRegister.text.toString()
            val editor = sharedPreferences.edit()

            if (passwordValue == confirmPasswordValue) {
                editor.putString("username", usernameValue)
                editor.putString("email", emailValue)
                editor.putString("password", passwordValue)
                editor.putString("confirm_password", confirmPasswordValue)
                editor.apply()
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Login::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "The password confirmation does not match", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}