package com.example.kurirkitabusines.Activity.ProfileActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.LoginActivity.LoginActivity
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.sukses_layout_dialog.view.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d("foto", "onCreate: ${Prefs.getString(Constant.PREF_USER_IMAGE, "")} ")
        Log.d("code", "onCreate: ${Prefs.getString(Constant.PREF_USER_CODE, "")} ")

        val imgUrl = Prefs.getString(Constant.PREF_USER_IMAGE , "")
        NamaDriver.text = Prefs.getString(Constant.PREF_USER_NAME, "")
        idDriver.text = Prefs.getString(Constant.PREF_USER_CODE , "")
        NoTelfonDriver.text  = Prefs.getString(Constant.PREF_USER_PHONE, "")
        EmailDriver.text = Prefs.getString(Constant.PREF_USER_EMAIL , "")
        TvAlamat.text = Prefs.getString(Constant.PREF_USER_ADDRESS , "")

        Glide.with(this)
            .load("http://kurirkita.id/images/users/1623321288_bgsplash.jpg")
            .into(profile_image_driver)


        btnBackProfil.setOnClickListener {
            PindahHalamanProfilKeDashboard()
        }

        btnLogout.setOnClickListener {
            val eBuilder = AlertDialog.Builder(this)
            //set Tittle
            eBuilder.setTitle("Keluar")
            //set Icon
            eBuilder.setIcon(R.drawable.ic_baseline_warning_24)
            eBuilder.setMessage("Apakah Yakin Anda Ingin Keluar")
            eBuilder.setPositiveButton("Ya"){
                    Dialog,which ->
                Prefs.clear()
                PindahAcitivity()
            }
            eBuilder.setNegativeButton("No"){
                    Dialog,which->
                Toast.makeText(this, "Jika Ingin Keluar Tekan Tombol ini Lagi" , Toast.LENGTH_SHORT)
            }
            //Menampilkan Pesan Dialog
            val createBuild = eBuilder.create()
            createBuild.show()
        }

    }


    private fun PindahHalamanProfilKeDashboard(){
        val go = Intent(this, DashboardActivity::class.java)
        startActivity(go)
    }

    private fun PindahAcitivity() {

        val view = View.inflate(this@ProfileActivity, R.layout.sukses_layout_dialog, null)
        val builder = android.app.AlertDialog.Builder(this@ProfileActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, LoginActivity::class.java)
            startActivity(go)
            finish()
        }
    }

//    private fun image() {
//        AndroidNetworking.post()
//    }
}
