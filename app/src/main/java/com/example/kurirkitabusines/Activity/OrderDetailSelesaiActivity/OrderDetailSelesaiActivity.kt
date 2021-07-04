package com.example.kurirkitabusines.Activity.OrderDetailSelesaiActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Adapter.AdapterItem.AdapterTimeList
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelOrder.ModelDetailOrder.ResponseModelDetailOrder
import com.example.kurirkitabusines.Model.ModelTimeList.ResponseModelListTime
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_order_detail_selesai.*
import okhttp3.Response

class OrderDetailSelesaiActivity : AppCompatActivity() {

    private lateinit var adapterTimeList: AdapterTimeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_detail_selesai)
        Log.d("id", "onCreate: ${(intent.getStringExtra("id").toString())} ")
        ListOrderDetailTimeList()
        DetailBarangSelesai()

        btnBackDetailPengirimanBarangSelesai.setOnClickListener {
            PindahAcitivityDashBoard()
        }
    }



    private fun DetailBarangSelesai(){
        progressBarDetailSelesai.visibility = View.VISIBLE
        AndroidNetworking.get(Config.detailOrder(intent.getStringExtra("id").toString()))
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsOkHttpResponseAndObject(ResponseModelDetailOrder::class.java,
            object : OkHttpResponseAndParsedRequestListener<ResponseModelDetailOrder>{
                override fun onResponse(
                    okHttpResponse: Response, response: ResponseModelDetailOrder
                ) {
                    if (okHttpResponse.isSuccessful){
                        if (response.status!!){
                            progressBarDetailSelesai.visibility = View.GONE
                            id_pengirimanDetailSelesai.text = response.data?.code.toString()
                            TvProvinsi_orderDetailSelesai.text = response.data?.edifice?.district?.city?.province?.name.toString()
                            TvKotaDetailSelesai.text = response.data?.edifice?.district?.city?.name.toString()
                            TvDistrikDetailSelesai.text = response.data?.edifice?.district?.name.toString()
                            TvStatusDetailSelesai.text =  when (response.data?.status) {
                                0 -> "Barang masih diproses di kantor pusat"
                                1 -> "Barang telah sampai di cabang"
                                2 -> "Barang telah sampai di tempat tujuan"
                                else -> "Unknown status"
                            }
                            TvKategoriDetailSelesai.text = response.data?.edifice?.category.toString()
                            TvAlamatDetailSelesai.text = response.data?.edifice?.address.toString()
                            TvGedungDetailSelesai.text = response.data?.edifice?.name.toString()
                            TvDiterimaSelesai.text = response.data?.receiptName.toString()
//                            TvWaktuDiterimaSelesai.text = response.data?.location!![adapterTimeList].timeArrived
//                            response.data?.location?.let {
//                                adapterTimeList = AdapterTimeList(it, response.data.receiptName.toString())
//                                rvTimeLineSelesai.apply {
//                                    layoutManager = LinearLayoutManager(this@OrderDetailSelesaiActivity)
//                                    adapter = adapterTimeList
//                                }
//                            }
                        }
                    }
                }

                override fun onError(anError: ANError?) {

                    progressBarDetailSelesai.visibility = View.GONE
                    Log.d("Detail", "onError: ${anError!!.errorCode}")

                }

            })
    }

    private fun ListOrderDetailTimeList() {
//        progressBar.visibility = View.VISIBLE
        AndroidNetworking.get(Config.detailOrder(intent.getStringExtra("id").toString()))
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .setPriority(Priority.LOW)
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelListTime::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelListTime> {
                    override fun onResponse(
                        okHttpResponse: Response, response: ResponseModelListTime
                    ) {
                        if (okHttpResponse.isSuccessful) {
                            if (response.status!!) {


                            }
                        }
//                        progressBar.visibility = View.GONE
                    }

                    override fun onError(anError: ANError?) {
//                        progressBar.visibility = View.GONE
                    }
                })
    }

    private fun PindahAcitivityDashBoard(){
        val go = Intent(this, DashboardActivity::class.java)
        startActivity(go)
    }


}