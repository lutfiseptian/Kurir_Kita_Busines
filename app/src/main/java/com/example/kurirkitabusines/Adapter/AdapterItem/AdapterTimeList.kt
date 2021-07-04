package com.example.kurirkitabusines.Adapter.AdapterItem

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kurirkitabusines.Model.ModelOrder.ModelDetailOrder.LocationItem
import com.example.kurirkitabusines.R
import kotlinx.android.synthetic.main.item_timeline.view.*

class AdapterTimeList(private val list: List<LocationItem>, private val penerima: String?) :
    RecyclerView.Adapter<AdapterTimeList.ItemHolder>() {
    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTimeList.ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_timeline, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            TvJamPenerima.text = item.timeArrived.toString()
            TvPesanBarang.text = item.valueString.toString()
            Log.d("TimeLineAdapter", "onBindViewHolder: position $position")
            Log.d("TimeLineAdapter", "onBindViewHolder: size $itemCount")
//            if (position == 0){
//                timeline.visibility = View.GONE
//            }else if(position == 1){
//                timeline.visibility = View.VISIBLE
//            }
//            penerima?.let {
//                if (position + 1 == itemCount) {
//                    TvNamaPenerimaTimeLine.visibility = View.VISIBLE
//                    TvNamaPenerimaTimeLine.text = it
//                } else {
//                    TvNamaPenerimaTimeLine.visibility = View.GONE
//                }
//            }
        }

    }
}