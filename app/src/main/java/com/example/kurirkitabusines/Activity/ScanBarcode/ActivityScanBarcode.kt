package com.example.kurirkitabusines.Activity.ScanBarcode

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelOrder.ModelDetailOrder.ResponseModelDetailOrder
import com.example.kurirkitabusines.Model.ModelScanBarcode.ResponseModelScanBarcode
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_scan_barcode.*
import okhttp3.Response


class ActivityScanBarcode : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    val MY_CAMERA_PERMISSION_REQUEST  = 1111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)

        codeScanner = CodeScanner(this@ActivityScanBarcode , scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread{
                
//                Toast.makeText(this@ActivityScanBarcode , " scan Result: ${it.text}" , Toast.LENGTH_LONG).show()
                sendGoods(it.text)
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this@ActivityScanBarcode , " camera error: ${it.message}" , Toast.LENGTH_LONG).show()
            }
        }

       checkPermision()
    }

    fun checkPermision(){
    if (ContextCompat.checkSelfPermission(
            this@ActivityScanBarcode , Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {
        ActivityCompat
            .requestPermissions(
                this@ActivityScanBarcode,
                arrayOf(Manifest.permission.CAMERA),
                MY_CAMERA_PERMISSION_REQUEST
            )
    }else{
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==MY_CAMERA_PERMISSION_REQUEST&&grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun sendGoods(url: String){
        val dialog = ProgressDialog.show(this, "Loading", "Please wait")
        AndroidNetworking.post(url)
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelScanBarcode::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelScanBarcode> {
                    override fun onResponse(
                        okHttpResponse: Response, response: ResponseModelScanBarcode
                    ) {
                        if (okHttpResponse.isSuccessful){
                            dialog.dismiss()
                            Toast.makeText(
                                this@ActivityScanBarcode,
                                response.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("Detail", "onError: ${anError!!.errorCode}")
                        Toast.makeText(
                            this@ActivityScanBarcode,
                            "Failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        dialog.dismiss()
                    }

                })
    }
}