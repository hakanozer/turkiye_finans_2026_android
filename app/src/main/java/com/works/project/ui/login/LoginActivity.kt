package com.works.project.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.project.R
import com.works.project.data.repository.UserRepositoryImpl
import com.works.project.databinding.ActivityLoginBinding
import androidx.lifecycle.lifecycleScope
import com.works.project.data.remote.UserApi
import com.works.project.data.remote.login.UserLoginRequestDto
import com.works.project.domain.model.UserData
import com.works.project.domain.utils.ApiClient
import com.works.project.domain.utils.Validations
import com.works.project.ui.models.UserLoginModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: UserLoginModel by viewModels()

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    @Inject
    lateinit var validations: Validations

    @Inject
    lateinit var userApi: UserApi

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("nametag", "onCreate:" + validations)

        //binding.lBtnLogin.setOnClickListener { v -> btnLogin() }
        binding.lBtnLogin.setOnClickListener(btnLoginClickEvent)

        viewModel.userNumber.observe(this) {
            binding.lTxtChange.text = it.toString()
        }

        binding.lBtnChange.setOnClickListener {
            viewModel.actionPlus()
        }
    }

    fun btnLogin() {
        Toast.makeText(this, "Alert", Toast.LENGTH_SHORT).show()
    }

    val btnLoginClickEvent = View.OnClickListener{ v ->
        val email = binding.lTxtEmail.text.toString().trim()
        val password = binding.lTxtPassword.text.toString().trim()

        //val userRepositoryImpl = UserRepositoryImpl(null)
        lifecycleScope.launch {
            try {
                //var userData = userRepositoryImpl.login(email, password)
                val dto:  UserLoginRequestDto = UserLoginRequestDto(email, password)
                if (dto != null) {
                    userApi.userLogin(dto).enqueue(object : Callback<UserData> {
                        override fun onResponse(
                            call: Call<UserData?>,
                            response: Response<UserData?>
                        ) {
                            val res = response.body()
                            val status = response.code()
                            Log.d("nametag", "onResponse: " + status)
                            if (res != null && status == 200) {
                                editor.putString("token", res.data.access_token)
                                editor.apply()
                                Toast.makeText(this@LoginActivity, res.data.access_token, Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this@LoginActivity, "Username or Password Error", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(
                            call: Call<UserData?>,
                            t: Throwable
                        ) {
                            Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                        }

                    })

                } else {
                    Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }catch (err: Exception) {
                Toast.makeText(this@LoginActivity, err.message, Toast.LENGTH_SHORT).show()
            }
        }


    }
}