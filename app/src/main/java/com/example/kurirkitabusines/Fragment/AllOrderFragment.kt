package com.example.kurirkitabusines.Fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Activity.DashBoardActivity.DashboardActivity
import com.example.kurirkitabusines.Activity.DetailActivity.OrderDetailActivity
import com.example.kurirkitabusines.Adapter.AdapterItem.AdapterItemOrder
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelOrder.ModelListOrder.DataItem
import com.example.kurirkitabusines.Model.ModelOrder.ModelListOrder.ResponseModelListOrder
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_all_order.*
import kotlinx.android.synthetic.main.sukses_layout_dialog.view.*
import okhttp3.Response


class AllOrderFragment : Fragment() {
    private val TAG = "AllOrderFragment"
    private var isLoading: Boolean = false
    private var page = 1
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterItemOrder: AdapterItemOrder
    private lateinit var layoutManager: LinearLayoutManager
    private val listItem = ArrayList<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvOrder)
        layoutManager = LinearLayoutManager(activity)
        adapterItemOrder = AdapterItemOrder(listItem)
        recyclerView.layoutManager = this.layoutManager
        recyclerView.adapter = adapterItemOrder
        adapterItemOrder.setOnclickListener { dataItemItem, i ->
            val intent = Intent(activity, OrderDetailActivity::class.java)
            intent.putExtra("id", dataItemItem.id.toString())
            startActivityForResult(
                intent,
                DashboardActivity.REQ_CODE_PROCCESS
            )
            Log.d(
                "AllOrderFragment",
                "onResponse: ${dataItemItem.toString()}"
            )
        }
        ListOrderDetail()
        refreshApp()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val total = adapterItemOrder.itemCount
                Log.d(TAG, "onScrolled: $isLoading")
                if (visibleItemCount + pastVisibleItem >= total) {
                    Log.d(TAG, "onScrolled: $isLoading")

                    if (isLoading) {
                        isLoading = false
                        Log.d(TAG, "onScrolled: get item")
                        ListOrderDetail()
                    }
                }
            }
        })
    }

    private fun ListOrderDetail() {
        Log.d(TAG, "ListOrderDetail: get list $page")
        Log.d(
            TAG,
            "ListOrderDetail: role ${if (Prefs.getString(
                    Constant.PREF_USER_OFFICEROLENAMA,
                    ""
                ) == "cabang"
            ) Config.GroupOrder() else Config.listOrder()}"
        )
        progressBar.visibility = View.VISIBLE
        AndroidNetworking.get(if (Prefs.getString(Constant.PREF_USER_OFFICEROLENAMA ,"") == "pusat") Config.GroupOrder()else Config.listOrder())
            .addHeaders("Authorization", Prefs.getString(Constant.PREF_USER_TOKEN, ""))
            .addQueryParameter("status", "1")
            .addQueryParameter("page", page.toString())
            .addQueryParameter("per_page", "10")
            .build()
            .getAsOkHttpResponseAndObject(
                ResponseModelListOrder::class.java,
                object : OkHttpResponseAndParsedRequestListener<ResponseModelListOrder> {
                    override fun onResponse(
                        okHttpResponse: Response, response: ResponseModelListOrder
                    ) {
                        if (okHttpResponse.isSuccessful) {
                            if (response.status!!) {

                                progressBar.visibility = View.GONE
                                if (page == 1){
                                    listItem.clear()
                                }
                                response.data?.data?.let {
                                    for (i in it.indices) {
                                        listItem.add(it[i]!!.copy())
                                    }
                                }
                                if (response.data?.totalPages!! > page) {
                                    page += 1
                                    isLoading = true
                                } else {
                                    isLoading = false
                                }
                                adapterItemOrder.notifyDataSetChanged()
                            }
                        }
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(anError: ANError?) {
                        progressBar.visibility = View.GONE
                        Log.d(TAG, "onError: ${anError?.errorCode}")
                        if (anError?.errorCode == 0) {
                            AcitivityErorConnection()
                        } else {
                            Toast.makeText(
                                activity,
                                "Something wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d(TAG, "onError: ${anError?.errorCode}")
                            Log.d(TAG, "onError: ${anError?.errorBody}")
                            Log.d(TAG, "onError: ${anError?.errorDetail}")
                        }
                    }
                })
    }

    private fun refreshApp() {
        swipeRefresh.setOnRefreshListener {
            listItem.clear()
            adapterItemOrder.notifyDataSetChanged()
            Toast.makeText(activity, "Halaman Di Segarkan", Toast.LENGTH_SHORT).show()
            page = 1
            ListOrderDetail()
            if (swipeRefresh.isRefreshing){
                swipeRefresh.isRefreshing = false
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            Log.d("DashboardResult", "onActivityResult: ")
            if (requestCode == DashboardActivity.REQ_CODE_PROCCESS) {
                Log.d("DashboardResult", "onActivityResult: 3400")
            }
        }
    }
    private fun AcitivityErorConnection(){
        val view = View.inflate(activity ,R.layout.connection_layout_dialog , null)
        val builder  = AlertDialog.Builder(activity)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        view.btnOk.setOnClickListener {
            val go = Intent(activity, DashboardActivity::class.java)
            startActivity(go)
        }
    }
}