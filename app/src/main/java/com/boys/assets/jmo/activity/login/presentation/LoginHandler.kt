package com.boys.assets.jmo.activity.login.presentation

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.boys.assets.jmo.helper.InterfaceDialog
import com.boys.assets.jmo.utils.LogUtil
import com.boys.assets.jmo.activity.login.model.LoginReqModel
import com.boys.assets.jmo.activity.menu.MenuFragmentActivity
import com.boys.assets.jmo.data.local.LocalPreferences

class LoginHandler {

    companion object {

        private val TAG = this::class.java.simpleName

        fun onSuccess(
            context: LoginActivity,
            interfaceDialog: InterfaceDialog,
            localPreferences: LocalPreferences,
            it: LoginReqModel
        ) {
            // hide loading dialog
            interfaceDialog.dismisDialogLoading()

            // save to shared preferences
            localPreferences.save(LocalPreferences.PRF_LOGIN, it)

            // handle success form
            val intent = Intent(context, MenuFragmentActivity::class.java)
            context.startActivity(intent)
            context.finish()
        }

        fun onError(
            context: LoginActivity,
            interfaceDialog: InterfaceDialog,
            localPreferences: LocalPreferences,
            it: String
        ) {
            // log messages
            LogUtil.e(TAG, "exceptionResult: $it")

            // hide loading dialog
            interfaceDialog.dismisDialogLoading()

            // show dialog exception
            interfaceDialog.showDialogWarningConfirm("Please try again!", it, "OK!")
        }

        fun formState(
            context: LoginActivity,
            it: LoginFormState,
            etEmail: EditText,
            etPassword: EditText,
            btnLogin: Button?
        ) {
            // disable login button unless both username / password is valid
            btnLogin!!.isEnabled = it.isDataValid

            if (it.usernameError != null) {
                etEmail.error = context.getString(it.usernameError)
            }
            if (it.passwordError != null) {
                etPassword.error = context.getString(it.passwordError)
            }
        }

    }

}