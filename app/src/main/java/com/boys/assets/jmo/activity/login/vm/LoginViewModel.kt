package com.boys.assets.jmo.activity.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.jmo.R
import com.boys.assets.jmo.activity.login.model.LoginReqModel
import com.boys.assets.jmo.activity.login.presentation.LoginFormState
import com.boys.assets.jmo.helper.DataSimulation
import com.boys.assets.jmo.helper.TypeValidator
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val onFormState: LiveData<LoginFormState> = _loginForm

    val onSuccess = MutableLiveData<LoginReqModel>()
    val onError = MutableLiveData<String>()

    fun doLogin(model : LoginReqModel){
        viewModelScope.launch {
            if (model.email == DataSimulation.usersEmail && model.password == DataSimulation.usersPassword){
                onSuccess.value = model
            }else{
                onError.value = "Invalid Username or Password"
            }
        }
    }

    fun dataChanged(username: String, password: String) {
        if (!TypeValidator.isEmailValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!TypeValidator.isPasswordValid(password, 8)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}