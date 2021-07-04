package com.example.kurirkitabusines.Activity.LoginActivity

import android.app.AlertDialog
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.MenuDashboard.ActivityPilihanMenuDashboard
import com.example.kurirkitabusines.Activity.ForgetPassword.ForgetPasswordActivity
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelLogin.ModelLoginResponse
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.sukses_layout_dialog.view.*
import okhttp3.Response

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        txtLupaPassword.setOnClickListener {
            val go = Intent(this,
                ForgetPasswordActivity::class.java)
            startActivity(go)
        }

        btnLogin.setOnClickListener {
            val phone_number = inputHp.text.toString()
            val pasword = inputPassword.text.toString()
            if (phone_number.isEmpty()){
                inputHp.error = "Nomer Telepon Kosong"
                inputHp.requestFocus()
            }else if (pasword.isEmpty()){
                inputPassword.error = "Masukan Password"
                inputPassword.requestFocus()
            }else{
                login()
            }
        }
    }
    

    private fun login(){
        val param = HashMap<String,String>().apply {
            put("username_or_phone",inputHp.text.toString())
            put("password",inputPassword.text.toString())
        }
        AndroidNetworking.post(Config.loginUrl())
            .addApplicationJsonBody(param)
            .build()
            .getAsOkHttpResponseAndObject(
                ModelLoginResponse::class.java,
                object : OkHttpResponseAndParsedRequestListener<ModelLoginResponse> {
                    override fun onResponse(okHttpResponse: Response, response: ModelLoginResponse) {
                       if (okHttpResponse.isSuccessful){
                           if(response.status == true){
                               Prefs.putString(Constant.PREF_USER_CODE, response.data?.user?.code.toString())
                               Prefs.putString(Constant.PREF_USER_NAME,response.data?.user?.name.toString())
                               Prefs.putString(Constant.PREF_USER_ADDRESS,response.data?.user?.address.toString())
                               Prefs.putString(Constant.PREF_USER_STATUS,response.data?.user?.status.toString())
                               Prefs.putString(Constant.PREF_USER_PHONE,response.data?.user?.phone.toString())
                               Prefs.putString(Constant.PREF_USER_TOKEN,response.data?.tokenAccess.toString())
                               Prefs.putString(Constant.PREF_USER_EMAIL,response.data?.user?.email.toString())
                               Prefs.putString(Constant.PREF_USER_OFFICE,response.data?.user?.office?.name.toString())
                               Prefs.putString(Constant.PREF_USER_OFFICEROLENAMA,response.data?.user?.office?.user?.role?.name.toString())
                               Prefs.putString(Constant.PREF_USER_IMAGE,response.data?.user?.photo.toString())
                               Prefs.putBoolean(Constant.PREF_ISLOGIN,true)
                               PindahAcitivity()
                           }else{
                               AcitivityEror()
                           }
                       }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("errorrrrrr", "onError: ${anError.toString()}")
                    }
                })
    }
    private fun PindahAcitivity(){

        val view = View.inflate(this@LoginActivity,R.layout.sukses_layout_dialog , null)
        val builder  = AlertDialog.Builder(this@LoginActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, ActivityPilihanMenuDashboard::class.java)
            startActivity(go)
            finish()
        }


    }

    private fun AcitivityEror(){

        val view = View.inflate(this@LoginActivity,R.layout.failed_layout_dialog , null)
        val builder  = AlertDialog.Builder(this@LoginActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, LoginActivity::class.java)
            startActivity(go)
            finish()
        }


    }



}
