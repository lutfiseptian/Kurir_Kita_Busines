package com.example.kurirkitabusines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hanks.passcodeview.PasscodeView
import kotlinx.android.synthetic.main.activity_verifikasi.*

class VerifikasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)
        passcodeView.setPasscodeLength(4).listener = object: PasscodeView.PasscodeViewListener{
            override fun onSuccess(number: String?) {
                if (number?.trim()?.length == 4){
                    val go = Intent(this@VerifikasiActivity , VerifikasiPasswordBaruActivity::class.java)
                    startActivity(go)
                }
            }
            override fun onFail() {
                val go = Intent(this@VerifikasiActivity , VerifikasiActivity::class.java)
                startActivity(go)
            }
        }
    }
}