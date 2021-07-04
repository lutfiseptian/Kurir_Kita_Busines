package com.example.kurirkitabusines.Adapter.AdapterItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kurirkitabusines.Model.ModelSend.ResponseModelSend
import com.example.kurirkitabusines.R
import kotlinx.android.synthetic.main.item_order_proses.view.*
import kotlinx.android.synthetic.main.item_order_proses.view.btnProsesBarang

class AdapterItemProses(private val list: List<ResponseModelSend>) :
    RecyclerView.Adapter<AdapterItemProses.ItemHolder>() {
    inner class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemProses.ItemHolder {

        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_proses, parent , false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterItemProses.ItemHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            id_pengirimanProses.text = item.data?.id.toString()
            TvProvinsi_orderProses.text = item.data?.group?.office?.city?.province?.name.toString()
            TvKotaProses.text  = item.data?.group?.office?.city?.name.toString()
            TvDistrikProses.text = item.data?.edifice?.district?.name.toString()
            TvKategoriProses.text = item.data?.edifice?.category.toString()
            TvAlamatproses.text = item.data?.edifice?.address.toString()
            TvGedungProses.text = item.data?.edifice?.name.toString()
            btnProsesBarang.setOnClickListener {
                itemClick?.let { v ->
                    v(item,position)
                }
            }

        }

    }
    private var itemClick: ((ResponseModelSend, Int) -> Unit)? = null

    fun setOnclickListener(listener: ((ResponseModelSend, Int) -> Unit)) {
        itemClick = listener
    }


}