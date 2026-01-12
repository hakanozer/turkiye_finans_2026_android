package com.works.project.ui.login

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.project.R
import com.works.project.data.repository.UserRepositoryImpl
import com.works.project.databinding.ActivityLoginBinding
import androidx.lifecycle.lifecycleScope // Import lifecycleScope
import kotlinx.coroutines.launch // Import launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //binding.lBtnLogin.setOnClickListener { v -> btnLogin() }
        binding.lBtnLogin.setOnClickListener(btnLoginClickEvent)
    }

    fun btnLogin() {
        Toast.makeText(this, "Alert", Toast.LENGTH_SHORT).show()
    }

    val btnLoginClickEvent = View.OnClickListener{ v ->
        val email = binding.lTxtEmail.text.toString().trim()
        val password = binding.lTxtPassword.text.toString().trim()

        val userRepositoryImpl = UserRepositoryImpl(null)
        lifecycleScope.launch {
            try {
                var userData = userRepositoryImpl.login(email, password)
                if (userData != null) {
                    Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }catch (err: Exception) {
                Toast.makeText(this@LoginActivity, err.message, Toast.LENGTH_SHORT).show()
            }
        }


    }
}