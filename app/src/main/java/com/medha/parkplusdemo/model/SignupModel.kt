package com.medha.parkplusdemo.model

import java.util.regex.Pattern

class SignupModel {
    val EMAIL_REGEX = "^([a-zA-Z0-9_\\-.+]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$"

    var email: String = ""

    var password: String = ""

    var confirmPassword:String = ""

    var name:String = ""

    fun isPasswordValid(password:String):Boolean {
        if(password.length<8){
            return false
        }
        return true
    }

    fun doPasswordsMatch(password: String,confirmPassword:String):Boolean{
        return password.equals(confirmPassword) && isPasswordValid(password) && isPasswordValid(confirmPassword)
    }

    fun isValidEmail(email: String?): Boolean {
        return if (email == null) {
            false
        } else {
            return Pattern.compile(EMAIL_REGEX).matcher(email).matches()
        }
    }

    fun isValidName(name: String?): Boolean {
        return !name.isNullOrEmpty() && name.length>3
    }
}