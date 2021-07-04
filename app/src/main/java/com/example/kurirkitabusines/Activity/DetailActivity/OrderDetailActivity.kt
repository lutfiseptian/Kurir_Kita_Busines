package com.example.kurirkitabusines.Activity.DetailActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.KonfirmasiActivity.KonfirmasiActivity
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelEvent.ModelEventDashboard
import com.example.kurirkitabusines.Model.ModelOrder.ModelDetailOrder.ResponseModelDetailOrder
import com.example.kurirkitabusines.Model.ModelSend.ResponseModelSend
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.fragment_complete.*
import okhttp3.Response
import org.greenrobot.eventbus.EventBus

class OrderDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)


        Log.d("cekId", (intent.getStringExtra("id").toString()))

        DetailBarang()


        btnBackDetailPengrimanBarang.setOnClickListener {
            PindahKeDasboard()
        }

        btnProsesBarang.setOnClickListener {

            val eBuilder = AlertDialog.Builder(this)
            //set Tittle
            eBuilder.setTitle("Proses")
            //set Icon
            eBuilder.setIcon(R.drawable.ic_baseline_check_24)
            eBuilder.setMessage("Apakah Yakin Anda Ingin Proses Barang")
            eBuilder.setPositiveButton("Ya") { Dialog, which ->
                BarangDikirim()
                PindahActivityKonfirmasi()
            }
            eBuilder.setNegativeButton("No") { Dialog, which ->
                Toast.makeText(this, "Jika Ingin Keluar Tekan Tombol ini Lagi", Toast.LENGTH_SHORT)
            }
            //Menampilkan Pesan Dialog
            val createBuild = eBuilder.create()
            createBuild.show()
        }




    }

    private fun PindahActivityKonfirmasi() {
        val i = Intent(this@OrderDetailActivity, KonfirmasiActivity::class.java)
        i.putExtra("id", intent.getStringExtra("id").toString())
        startActivity(i)
    }

    private fun DetailBarang() {
        progressBarDetail.visibility = View.VISIBLE

        Log.d("cekIdBarangDetail", (intent.getStringExtra("id").toString()))
        AndroidNetworking.get(Config.detailOrder(intent.getStringExtra("id").toString()))
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelDetailOrder::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelDetailOrder> {
                    override fun onResponse(
                        okHttpResponse: Response, response: ResponseModelDetailOrder
                    ) {
                        if (okHttpResponse.isSuccessful) {
                            if (response.status!!) {
                                Log.d("Detail", "onResponse: ${response.data!!.id.toString()}")
                                id_pengirimanDetail.text = response.data?.code.toString()
                                TvProvinsi_orderDetail.text =
                                    response.data?.edifice?.district?.city?.province?.name.toString()
                                TvKotaDetail.text =
                                    response.data?.edifice?.district?.city?.name.toString()
                                TvDistrikDetail.text =
                                    response.data?.edifice?.district?.name.toString()
                                TvAlamatDetail.text = response.data.edifice?.address.toString()
                                TvKategoriDetail.text = response.data.edifice?.category.toString()
                                TvGedungDetail.text = response.data?.edifice?.name.toString()
                                TvDiterima.text = response.data.receiptName.toString()
                                TvStatusDetail.text = when (response.data.status) {
                                    0 -> "Barang masih diproses di kantor pusat"
                                    1 -> "Barang telah sampai di cabang"
                                    2 -> "Barang telah sampai di tempat tujuan"
                                    else -> "Unknown status"
                                }

                            } else {
//                                Toast.makeText(this , "$")
                            }
                        }
                        progressBarDetail.visibility = View.GONE
                    }

                    override fun onError(anError: ANError?) {
                        progressBarDetail.visibility = View.GONE
                        if (anError?.errorCode == 0) { Toast.makeText(this@OrderDetailActivity,
                            "No Internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                        } else {
                            Toast.makeText(this@OrderDetailActivity,
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

    private fun BarangDikirim() {
        Log.d("cekIdBarangKirim", (intent.getStringExtra("id").toString()))
        AndroidNetworking.post(Config.SendOrder(intent.getStringExtra("id").toString()))
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelSend::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelSend> {
                    override fun onResponse(
                        okHttpResponse: Response, response: ResponseModelSend
                    ) {
                        if (okHttpResponse.isSuccessful) {
                            if (response.status == true) {
                                Log.d("IdKiriman", "onResponse: ${response.data!!.id.toString()}")
//                                setResult(Activity.RESULT_OK)
//                                EventBus.getDefault().post(
//                                    ModelEventDashboard(
//                                        2
//                                    )
//                                )
//                                finish()
                            } else {
//                                Toast.makeText(
//                                    this@OrderDetailActivity,
//                                    response.message,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                Log.d("OrderDetail", "onResponse: ${response.message}")
                            }
                        }
                    }

                    override fun onError(anError: ANError?) {
                        if (anError?.errorCode == 0) {
                            Toast.makeText(
                                this@OrderDetailActivity,
                                "No Internet Connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@OrderDetailActivity,
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

    private fun PindahKeDasboard(){
        val go = Intent(this , DashboardActivity::class.java)
        startActivity(go)
    }

}

