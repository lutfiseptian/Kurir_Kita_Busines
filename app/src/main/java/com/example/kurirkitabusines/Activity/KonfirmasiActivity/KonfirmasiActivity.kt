package com.example.kurirkitabusines.Activity.KonfirmasiActivity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.LoginActivity.LoginActivity
import com.example.kurirkitabusines.Fragment.AllOrderFragment
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelArrived.ResponseModelArrived
import com.example.kurirkitabusines.Model.ModelEvent.ModelEventDashboard
import com.example.kurirkitabusines.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_konfirmasi.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.sukses_layout_dialog.view.*
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import java.io.File
class KonfirmasiActivity : AppCompatActivity() {
    private  var imagePicker:ImageView?= null
    private var fileGambar:File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi)

        imagePicker = findViewById(R.id.imgBarangKonfirmasi)
        val camera = findViewById<ImageView>(R.id.imgBarangKonfirmasi)

//        Log.d("cekIdBarangKonfimasi", (intent.getStringExtra("id").toString()))
//
//        KonfirmasiBarang()



        btnKonfirmasiBarang.setOnClickListener {
            val eBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
            val nama_penerima = inputNamaPenerima.text.toString()
            val pasword_driver = inputPasswordDriver.text.toString()
            if (nama_penerima.isEmpty()){
                inputHp.error = "Masukan Nama Penerima"
                inputHp.requestFocus()
            }else if (pasword_driver.isEmpty()){
                inputPassword.error = "Masukan Password"
                inputPassword.requestFocus()
            }else{
                //set Tittle
                eBuilder.setTitle("Selesai")
                //set Icon
                eBuilder.setIcon(R.drawable.ic_baseline_done_24)
                eBuilder.setMessage("Pastikan Penerima Dan Foto Sudah Jelas !!!")
                eBuilder.setPositiveButton("Ya"){
                        Dialog,which ->

                    KonfirmasiBarang()
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



        btnBatalBarang.setOnClickListener {
            val eBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
            eBuilder.setTitle("Batal")
            eBuilder.setMessage("Batalkan Order")
            eBuilder.setIcon(R.drawable.ic_baseline_warning_24)
            eBuilder.setPositiveButton("Ya"){
                    Dialog,which ->
                val go = Intent(this , DashboardActivity::class.java)
                startActivity(go)
            }
            eBuilder.setNegativeButton("no"){
                    Dialog,which ->
                Toast.makeText(this, "Jika Ingin Keluar Tekan Tombol ini Lagi", Toast.LENGTH_SHORT)
            }

            val  createBuild = eBuilder.create()
            createBuild.show()
        }


        camera.setOnClickListener {
            ImagePicker.with(this)
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(720, 720)  //Final image resolution will be less than 1080 x 1080(Optional)
                .cameraOnly().crop().start()
        }
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
//            val fileUri = data?.data
            //You can get File object from intent
            fileGambar = ImagePicker.getFile(data)!!
            val uri: Uri = Uri.fromFile(fileGambar)
            imgBarangKonfirmasi.setImageURI(uri)
            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!
            Log.d("FOTOOOO", "onActivityResult: $filePath")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun KonfirmasiBarang(){
        val param = HashMap<String,String>().apply {
            put("receipt_name",inputNamaPenerima.text.toString())
            put("password",inputPasswordDriver.text.toString()) }
        progressBarKonfirmasi.visibility = View.VISIBLE
        Log.d("cekIdBarangKonfirmasi", (intent.getStringExtra("id").toString()))
        AndroidNetworking.upload(Config.ArrivedOrder(intent.getStringExtra("id").toString()))
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN , ""))
            .addMultipartParameter(param)
            .addMultipartFile("photo", fileGambar)
            .setPriority(Priority.HIGH)
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelArrived::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelArrived> {
                    override fun onResponse(okHttpResponse: Response, response: ResponseModelArrived
                    ) {
                       if (okHttpResponse.isSuccessful){
                           if (response.status == true){
                               Log.d("IdBarangKonfirmasi", "onResponse: ${response.data!!.status.toString()}")
                               ActivitySukses()
                           }else{
                               AcitivityEror()
                           }
                       }
                        progressBarKonfirmasi.visibility = View.GONE
                    }

                    override fun onError(anError: ANError?) {
                        progressBarDetail.visibility = View.GONE
                        if (anError?.errorCode == 0) {
                            AcitivityErorConnection()
                        } else {
                            Toast.makeText(
                                this@KonfirmasiActivity,
                                "Something wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("OrderError", "onError: ${anError?.errorCode}")
                            Log.d("OrderError", "onError: ${anError?.errorBody}")
                            Log.d("OrderError", "onError: ${anError?.errorDetail}")
                        }
                    }
                }
            )
    }

    private fun ActivitySukses(){

        val view = View.inflate(this@KonfirmasiActivity,R.layout.sukses_layout_dialog , null)
        val builder  = AlertDialog.Builder(this@KonfirmasiActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, DashboardActivity::class.java)
            startActivity(go)
            finish()
        }


    }

    private fun AcitivityEror(){

        val view = View.inflate(this@KonfirmasiActivity,R.layout.failed_layout_dialog , null)
        val builder  = AlertDialog.Builder(this@KonfirmasiActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        view.btnOk.setOnClickListener {
            val go = Intent(this, KonfirmasiActivity::class.java)
            startActivity(go)
            finish()
        }
    }

    private fun AcitivityErorConnection(){

        val view = View.inflate(this@KonfirmasiActivity ,R.layout.connection_layout_dialog , null)
        val builder  = AlertDialog.Builder(this@KonfirmasiActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        view.btnOk.setOnClickListener {
            val go = Intent(this@KonfirmasiActivity, DashboardActivity::class.java)
            startActivity(go)
        }
    }
    }
