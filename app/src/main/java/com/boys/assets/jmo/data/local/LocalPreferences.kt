package com.boys.assets.jmo.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.boys.assets.jmo.activity.login.model.LoginReqModel

class LocalPreferences (val context: Context) {

    companion object {

        val PRF_LOGIN       = "PRF_LOGIN"

    }

    @SuppressLint("CommitPrefEdits")
    fun save(KEY_NAME : String, data : Any) {

        /**
         * KEY_NAME to save generate otp response
         */
        if (KEY_NAME == PRF_LOGIN){
            val sharedPreference: SharedPreferences = context.getSharedPreferences(PRF_LOGIN, Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            val model = data as LoginReqModel
                editor.putString("id", java.util.UUID.randomUUID().toString())
                editor.putString("email", model.email)
                editor.putString("password", model.password)
                editor.apply()
        }

    }

    fun get(KEY_NAME : String) : Any? {
        var model : Any? = null

        /**
         * KEY_NAME to get generate otp response
         */
        if (KEY_NAME == PRF_LOGIN){
            val sharedPreference: SharedPreferences = context.getSharedPreferences(PRF_LOGIN, Context.MODE_PRIVATE)

            model = LoginReqModel(
                email = sharedPreference.getString("email", null),
                password = sharedPreference.getString("password", null)
            )
        }

        return model
    }

}