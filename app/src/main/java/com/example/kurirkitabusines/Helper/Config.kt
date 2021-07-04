package com.example.kurirkitabusines.Helper

object Config {
    private fun BASE_URL():String{
        return "http://kurirkita.id/api"
    }

    fun loginUrl():String{
        return BASE_URL()+"/user/login"
    }

    fun detailProfil():String{
        return BASE_URL()+"/profile"
    }

    fun listOrder():String{
        return  BASE_URL()+"/transaction"
    }


    fun detailOrder(id: String):String{
        return BASE_URL()+"/transaction/$id"
    }

    fun ArrivedOrder(id : String):String{
        return  BASE_URL()+"/transaction/arrived/$id"
    }

    fun SendOrder(id : String):String{
        return  BASE_URL()+"/transaction/send/$id"
    }

    fun forgotPassword():String{
        return BASE_URL()+"/user/forgot-password"
    }

    fun GroupOrder():String{
       return BASE_URL()+"/group"
    }
}