package com.example.kurirkitabusines.Activity.DashBoardActivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kurirkitabusines.Activity.ScanBarcode.ActivityScanBarcode
import com.example.kurirkitabusines.Activity.ProfileActivity.ProfileActivity
import com.example.kurirkitabusines.Adapter.AdapterFragment.ViewPagerAdapter
import com.example.kurirkitabusines.Fragment.AllOrderFragment
import com.example.kurirkitabusines.Fragment.CompletedOrderFragment
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelEvent.ModelEventDashboard
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DashboardActivity : AppCompatActivity() {

    companion object {
        val REQ_CODE_PROCCESS = 4200
    }

    private lateinit var ctx : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        NamaUser.text = Prefs.getString(Constant.PREF_USER_NAME, "")
        ctx = this
        setUpTabs()

        Profile_Image.setOnClickListener {
            PindahAcitivity()
            finish()
        }
        btnScanBarcodeDashboard.setOnClickListener{
            val go = Intent(this@DashboardActivity , ActivityScanBarcode::class.java)
            startActivity(go)
        }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AllOrderFragment(),"Semua Order")
//        adapter.addFragment(ProsesOrderFragment(),"Proses")
        adapter.addFragment(CompletedOrderFragment(),"Selesai")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    private fun PindahAcitivity(){
        val go = Intent(this, ProfileActivity::class.java)
        startActivity(go)
//        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d("DashboardResult", "onActivityResult: ")
            if (requestCode == REQ_CODE_PROCCESS) {
                Log.d("DashboardResult", "onActivityResult: 3400")
                val tab = tabs.getTabAt(1)
                tab?.select()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventMoveTab(eventDashboard: ModelEventDashboard){
        Log.d("Dashboard", "eventMoveTab: run")
        tabs.getTabAt(eventDashboard.tabPosition)?.select()
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this@DashboardActivity)){
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this@DashboardActivity)){
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

}



