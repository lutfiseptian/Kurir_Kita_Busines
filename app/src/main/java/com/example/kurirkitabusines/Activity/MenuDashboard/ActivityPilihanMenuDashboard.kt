package com.example.kurirkitabusines.Activity.MenuDashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.ScanBarcode.ActivityScanBarcode
import com.example.kurirkitabusines.R
import kotlinx.android.synthetic.main.activity_pilihan_menu_dashboard.*

class ActivityPilihanMenuDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilihan_menu_dashboard)

        btnMenuDashboard.setOnClickListener{
            val go  = Intent(this@ActivityPilihanMenuDashboard , DashboardActivity::class.java)
            startActivity(go)
        }

        btnScanBarcode.setOnClickListener{
            val go = Intent(this@ActivityPilihanMenuDashboard , ActivityScanBarcode::class.java)
            startActivity(go)
        }
    }

}