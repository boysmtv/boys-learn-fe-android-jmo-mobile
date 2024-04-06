package com.boys.assets.jmo.activity.login.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.jmo.activity.login.model.LoginReqModel
import com.boys.assets.jmo.activity.login.vm.LoginViewModel
import com.boys.assets.jmo.data.local.LocalPreferences
import com.boys.assets.jmo.databinding.ActivityLoginBinding
import com.boys.assets.jmo.helper.DataSimulation
import com.boys.assets.jmo.helper.InterfaceDialog
import com.boys.assets.jmo.utils.afterTextChanged
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@LoginActivity

    private lateinit var binding            : ActivityLoginBinding
    private lateinit var localPreferences   : LocalPreferences
    private lateinit var interfaceDialog    : InterfaceDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        interfaceDialog     = InterfaceDialog(thisContext)
        localPreferences    = LocalPreferences(thisContext)
        binding             = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar!!.hide()

        val etEmail          = binding.etEmail
        val etPassword       = binding.etPassword
        val tvRegister       = binding.tvRegister
        val tvForgotPassword = binding.tvForgotPassword
        val btnLogin         = binding.btnLogin

        val viewModel by viewModel<LoginViewModel>()

        setViewModel(viewModel)
        setValidationForm(viewModel, etEmail, etPassword)
        getValidationForm(viewModel, etEmail, etPassword, btnLogin)
        setOnClickListener(viewModel, etEmail, etPassword, btnLogin, tvRegister, tvForgotPassword)

        setSimulationForm(binding)
    }

    /**
     * set view model login
     */
    private fun setViewModel(viewModel: LoginViewModel) {
        with(viewModel) {
            onSuccess.observe(thisContext) {
                LoginHandler.onSuccess(thisContext, interfaceDialog, localPreferences, it)
            }
            onError.observe(thisContext) {
                LoginHandler.onError(thisContext, interfaceDialog, localPreferences, it)
            }
        }
    }

    /**
     * handle form validation
     * this is just temporary
     */
    private fun getValidationForm(
        viewModel: LoginViewModel,
        etEmail: EditText,
        etPassword: EditText,
        btnLogin: Button?
    ) {
        with(viewModel) {
            onFormState.observe(thisContext) {
                LoginHandler.formState(thisContext, it, etEmail, etPassword, btnLogin)
            }
        }
    }

    /**
     * set validation form
     */
    private fun setValidationForm(
        viewModel: LoginViewModel,
        username: EditText,
        password: EditText
    ) {
        username.afterTextChanged {
            viewModel.dataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
        password.afterTextChanged {
            viewModel.dataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
    }


    /**
     * set onclick listener
     */
    private fun setOnClickListener(
        viewModel: LoginViewModel,
        etEmail: EditText,
        etPassword: EditText,
        btnLogin: MaterialButton,
        tvRegister: TextView,
        tvForgotPassword: TextView
    ) {
        /**
         * set onclick button login for do login to api
         */
        btnLogin.setOnClickListener {
            val model = LoginReqModel(
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
            )

            // set loading on ui
            interfaceDialog.showDialogLoading("Loading ...")

            // do send request login
            viewModel.doLogin(model)
        }

        /**
         * set onclick icon to register activity
         */
        tvRegister.setOnClickListener {

        }

        /**
         * set onclick icon to forgot password activity
         */
        tvForgotPassword.setOnClickListener {

        }
    }

    /**
     * set simulation form
     */
    private fun setSimulationForm(binding: ActivityLoginBinding){
        binding.etEmail.setText(DataSimulation.usersEmail)
        binding.etPassword.setText(DataSimulation.usersPassword)
    }

}