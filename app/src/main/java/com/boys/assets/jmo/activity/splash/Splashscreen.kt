package com.boys.assets.jmo.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.jmo.R
import com.boys.assets.jmo.activity.login.presentation.LoginActivity
import com.boys.assets.jmo.utils.LogUtil

@SuppressLint("CustomSplashScreen")
class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)
        supportActionBar!!.hide()

        callSplash()
    }

    private fun callSplash(){
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep((1 * 2000).toLong())

                val i = Intent(baseContext, LoginActivity::class.java)
                    startActivity(i)

                    //Remove activity
                    finish()
                } catch (e: Exception) {

                    LogUtil.e("Splashscreen", "Err : ${e.message}")

                }
            }
        }
        // start thread
        background.start()
    }
}