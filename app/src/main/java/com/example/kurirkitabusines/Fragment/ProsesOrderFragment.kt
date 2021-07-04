package com.example.kurirkitabusines.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.example.kurirkitabusines.Adapter.AdapterItem.AdapterItemOrder
import com.example.kurirkitabusines.Adapter.AdapterItem.AdapterItemProses
import com.example.kurirkitabusines.Helper.Config
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelArrived.ResponseModelArrived
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_order_proses.*
import okhttp3.Response


class ProsesOrderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterItemOrder: AdapterItemProses
    private val listItem = ArrayList<ResponseModelArrived>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_proses, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvOrderProses)


    }

    private fun ListOrderProses(id : String){
        progressBarProses.visibility = View.VISIBLE
        AndroidNetworking.post(Config.SendOrder(id))
    }

}