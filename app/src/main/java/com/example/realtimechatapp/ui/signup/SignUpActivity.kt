package com.example.realtimechatapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.realtimechatapp.R
import com.example.realtimechatapp.databinding.ActivitySignUpBinding
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.ui.signin.SignInViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        viewModel.appState.observe(this, Observer {
            if(it != null) {
                when(it) {
                    is AppState.Success -> {
                        Toast.makeText(this@SignUpActivity, "Successful Signed Up", Toast.LENGTH_SHORT).show()
                    }
                    is AppState.Error -> {
                        Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.signUpBtn.setOnClickListener {
            viewModel.signup(
                email = binding.emailEditText.text.toString(),
                username = binding.userNameEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }
    }
}