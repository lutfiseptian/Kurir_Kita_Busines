package com.example.kurirkitabusines.Activity.ForgetPassword

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.LoginActivity.LoginActivity
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Model.ModelForgotPassword.ModelForgotPasswordResponse
import com.example.kurirkitabusines.R
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.sukses_layout_dialog.view.*
import okhttp3.Response

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        btnKirimCode.setOnClickListener {
           forgotPassword()

        }
    }


    private fun forgotPassword(){
        val param = HashMap<String , String>().apply {
            put("email" , inputEmailForgot.text.toString())
        }
        AndroidNetworking.post(Config.forgotPassword())
            .addBodyParameter(param)
            .build()
            .getAsOkHttpResponseAndObject(
                ModelForgotPasswordResponse::class.java ,
                object : OkHttpResponseAndParsedRequestListener<ModelForgotPasswordResponse> {
                    override fun onResponse(okHttpResponse: Response, response: ModelForgotPasswordResponse)
                    {
                        if (okHttpResponse.isSuccessful){
                            if (response.status == true){
                                AcitivitySukses()
                            }else{
                                AcitivityEror()
                            }
                        }
                    }

                    override fun onError(anError: ANError?) {
                        AcitivityErorConnection()
                    }


                }
            )
    }

    private fun AcitivitySukses(){

        val view = View.inflate(this@ForgetPasswordActivity,
            R.layout.emailsucces_dialog, null)
        val builder  = AlertDialog.Builder(this@ForgetPasswordActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, LoginActivity::class.java)
            startActivity(go)
            finish()
        }


    }

    private fun AcitivityEror(){

        val view = View.inflate(this@ForgetPasswordActivity,
            R.layout.eror_email, null)
        val builder  = AlertDialog.Builder(this@ForgetPasswordActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, LoginActivity::class.java)
            startActivity(go)
            finish()
        }
    }

    private fun AcitivityErorConnection(){

        val view = View.inflate(this@ForgetPasswordActivity ,
            R.layout.connection_layout_dialog, null)
        val builder  = AlertDialog.Builder(this@ForgetPasswordActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        view.btnOk.setOnClickListener {
            val go = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
            startActivity(go)
        }
    }
}