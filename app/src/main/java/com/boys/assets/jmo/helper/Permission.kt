package com.boys.assets.jmo.helper

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.boys.assets.jmo.utils.LogUtil
import java.util.*

class Permission {

    companion object {

        /**
         * permissions request code
         */
        val PERSM_SDK_PERMISSION = 100
        val PERSM_ACCESS_FINE_LOCATION = 101
        val PERSM_CAMERA = 102
        val PERSM_WRITE_EXTERNAL_STORAGE = 103
        val PERSM_READ_EXTERNAL_STORAGE = 104

        /**
         * Permissions that need to be explicitly requested from end user.
         */
        val REQUIRED_SDK_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )

        /**
         * Checks the dynamically-controlled permissions and requests missing permissions from end user.
         */
        fun getPermission(activity: Activity, permissionsCode: Int, permissions: String){
            val arrPermission = arrayOf(permissions)
            ActivityCompat.requestPermissions(activity, arrPermission, permissionsCode)
        }

        fun getPermission(
            activity: Activity,
            REQUIRED_SDK_PERMISSIONS: Array<String>,
            REQUEST_CODE_ASK_PERMISSIONS: Int
        ) {
            val missingPermissions: MutableList<String> = ArrayList()
            // check all required dynamic permissions
            for (permission in REQUIRED_SDK_PERMISSIONS) {
                val result = ContextCompat.checkSelfPermission(activity, permission)
                if (result != PackageManager.PERMISSION_GRANTED) {
                    missingPermissions.add(permission)
                }
            }
            if (missingPermissions.isNotEmpty()) {
                // request all missing permissions
                val permissions = missingPermissions.toTypedArray()
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_ASK_PERMISSIONS)
            } else {
                val grantResults = IntArray(REQUIRED_SDK_PERMISSIONS.size)
                Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED)
            }
        }

        fun checkPermission(activity: Activity, permission: String): Boolean {
            var result = true
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED){
                result = false
            }
            return result
        }

        fun checkPermission(activity: Activity, permission: Array<String>): Boolean {
            var result = true
            permission.forEach {
                if (ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_DENIED){
                    result = false
                }
                LogUtil.e("Permission", "$it : $result")
            }
            return result
        }
    }
}