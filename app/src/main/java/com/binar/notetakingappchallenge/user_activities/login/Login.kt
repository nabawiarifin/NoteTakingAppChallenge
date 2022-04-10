package com.binar.notetakingappchallenge.user_activities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binar.notetakingappchallenge.MainActivity
import com.binar.notetakingappchallenge.databinding.LoginBinding
import com.binar.notetakingappchallenge.user_activities.register.Register


class Login: AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = LoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = this.getSharedPreferences("userdata",Context.MODE_PRIVATE)

        val sharedUsernameValue = sharedPreferences.getString("username", "")
        val sharedPasswordValue = sharedPreferences.getString("password","")

        binding.btnLogin.setOnClickListener {
            val usernameValue = binding.etUsernameLogin.text.toString()
            val passwordValue = binding.etPasswordLogin.text.toString()

            if (usernameValue == sharedUsernameValue && passwordValue == sharedPasswordValue) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,"Username or Password is wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }
}