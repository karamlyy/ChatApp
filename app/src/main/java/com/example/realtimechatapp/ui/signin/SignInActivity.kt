package com.example.realtimechatapp.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.realtimechatapp.databinding.ActivitySignInBinding
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.ui.main.MainActivity
import com.example.realtimechatapp.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]


        viewModel.appState.observe(this, Observer {
            if(it != null) {
                when(it) {
                    is AppState.Success -> {
                        Toast.makeText(this@SignInActivity, "Successful Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    is AppState.Error -> {
                        Toast.makeText(this@SignInActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.signInBtn.setOnClickListener {
            viewModel.login(
                username = binding.userNameEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        binding.changeToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}