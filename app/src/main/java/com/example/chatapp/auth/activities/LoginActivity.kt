package com.example.chatapp.auth.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.auth.factory.SignInViewModelFactory
import com.example.chatapp.auth.repository.SignInRepository
import com.example.chatapp.network.NetworkUtils
import com.example.chatapp.storage.BaseActivity
import com.example.chatapp.auth.viewModel.SignInViewModel
import com.example.chatapp.chat.activities.ChatListUsersActivity
import com.example.chatapp.retrofit.RetrofitClient
import com.example.chatapp.storage.AppReferences
import kotlinx.android.synthetic.main.activity_login.btn_sign_in
import kotlinx.android.synthetic.main.activity_login.et_email_sign_in
import kotlinx.android.synthetic.main.activity_login.et_password_sign_in
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    private lateinit var networkUtils: NetworkUtils

    private lateinit var signInViewModel: SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkUtils = NetworkUtils(this)

        initView()
    }

    private fun initView() {
        val signInRepository = SignInRepository(RetrofitClient.instance)
        val factory = SignInViewModelFactory(signInRepository)
        signInViewModel = ViewModelProvider(this@LoginActivity, factory)[SignInViewModel::class.java]

        btn_sign_in.setOnClickListener {
            if (networkUtils.isNetworkAvailable()) {
                signIn()
            } else {
                showErrorSnackBar("No internet connection", true)
            }
        }

    }

    private fun signIn() {
        val email = et_email_sign_in.text.toString().trim()
        val password = et_password_sign_in.text.toString().trim()

        if (isValidInput()) {
            showProgressDialog(this@LoginActivity, "Signing in...")
            signInViewModel.signIn(email, password)

            signInViewModel.signInResponseLiveData.observe(this) { response ->
                hideProgressDialog()

                response?.let {

                    val token = it.token
                    val userId = it.userId

                    Log.e("SignInActivity", "Login successful: TokenSignUp - ${it.token}")

                    AppReferences.setToken(this@LoginActivity, token)

                    AppReferences.setLoginState(this@LoginActivity, true)

                    AppReferences.setUserId(this@LoginActivity, userId)

                    Log.e("SignInActivity", "User Id is $userId")

                    startActivity(Intent(this@LoginActivity, ChatListUsersActivity::class.java))
                }
            }

            signInViewModel.errorLiveData.observe(this) { error ->
                hideProgressDialog()

                error?.let {
                    try {
                        val errorMessage = JSONObject(error).getString("message")
                        Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

    private fun isValidInput(): Boolean {
        val email = et_email_sign_in.text.toString().trim()
        val password = et_password_sign_in.text.toString().trim()

        if (email.isEmpty()) {
            et_email_sign_in.error = "Email is not allowed to be empty."
            return false
        } else if (!isValidEmail(email)) {
            et_email_sign_in.error = "Please enter a valid email address."
            return false
        }

        if (password.isEmpty()) {
            et_password_sign_in.error = "Password cannot be empty."
            return false
        }

        return true
    }

    private fun isValidEmail(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email!!).matches()
    }

}