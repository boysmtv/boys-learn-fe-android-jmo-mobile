package com.boys.assets.jmo.helper

import android.content.Context
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog

class GlobalHelper {

    companion object {

        val RC_OK = "OK"

        val RESP_SUCCESS = "success"
        val RESP_BAD_REQUEST = "BAD_REQUEST"
        val INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR"

        val RESP_MSG_EMAIL_INACTIVE = "Account is not activated"
        val RESP_MSG_EMAIL_EXIST = "User email already exist"

        val PERMISSION_REQUEST_CODE = 101

        // for get description of times second
        fun secondToMinutes(second : String) : String {
            if (second.toInt() > 60)
            {
                if (second.toInt() / 60 > 60){
                    if (second.toInt() / 60 / 60 > 24){
                        if (second.toInt() / 60 / 60 / 24 > 30){
                            if (second.toInt() / 60 / 60 / 24 / 12 > 100){
                                return (second.toInt() / 60 / 60 / 24 / 30 / 12).toString() + " tahun lalu"
                            }else{
                                return (second.toInt() / 60 / 60 / 24 / 30).toString() + " bulan lalu"
                            }
                        }else{
                            return (second.toInt() / 60 / 60 / 24).toString() + " hari lalu"
                        }
                    }else{
                        return (second.toInt() / 60 / 60).toString() + " jam lalu"
                    }
                }else{
                    return "${second.toInt() / 60} menit lalu"
                }
            }else{
                return "$second detik lalu"
            }
        }

        fun loadingOnUI(context: Context, title: String) : SweetAlertDialog {
            val pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
            pDialog.titleText = title
            pDialog.setCancelable(true)
            pDialog.show()
            return pDialog
        }

    }
}