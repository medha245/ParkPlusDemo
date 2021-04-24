package com.medha.parkplusdemo.model

import java.util.regex.Pattern

class LoginData {

    val EMAIL_REGEX = "^([a-zA-Z0-9_\\-.+]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$"

    var username: String = ""

    var password: String = ""


    fun isPasswordValid(password:String):Boolean {
        if(password.length<8){
            return false
        }
        return true
    }

    fun isValidEmail(email: String?): Boolean {
        return if (email == null) {
            false
        } else {
            return Pattern.compile(EMAIL_REGEX).matcher(email).matches()
        }
    }
}