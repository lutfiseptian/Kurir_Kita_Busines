package com.example.kurirkitabusines.Adapter.AdapterItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kurirkitabusines.Helper.Constant
import com.example.kurirkitabusines.Model.ModelOrder.ModelListOrder.DataItem
import com.example.kurirkitabusines.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.item_order.view.*
class AdapterItemOrder(private val list: List<DataItem>) :
    RecyclerView.Adapter<AdapterItemOrder.ItemHolder>() {
    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemOrder.ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val item = list[position]
        holder.itemView.apply {
                id_pengirimanOrder.text = item.code.toString()
                TvProvinsi_order.text = item.edifice?.subdistrict?.district?.city?.kabupatenKota.toString()
                TvKotaOrder.text = item.edifice?.subdistrict?.district?.kecamatan.toString()
                TvDistrikOrder.text = item.edifice?.subdistrict?.kelurahan.toString()
                TvGedungOrder.text = item.edifice?.name.toString()
                TvStatus.text = when(item.status){
                    0 -> "Barang masih diproses di kantor pusat"
                    1 -> "Barang telah sampai di cabang"
                    2 -> "Barang telah sampai di tempat tujuan"
//                    3 -> "Barang telah sampai di tempat tujuan"
                    else -> "Unknown status"
                }

            btnDetail.setOnClickListener {
                    itemClick?.let { v ->
                        v(item, position)
                    }
                }
        }
    }

    private var itemClick: ((DataItem, Int) -> Unit)? = null

    fun setOnclickListener(listener: ((DataItem, Int) -> Unit)) {
        itemClick = listener
    }

}
