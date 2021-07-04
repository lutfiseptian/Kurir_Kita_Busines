package com.example.kurirkitabusines.Activity.SplashActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.LoginActivity.LoginActivity
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({

            if(Prefs.getBoolean(Constant.PREF_ISLOGIN,false)) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}