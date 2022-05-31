package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.model.Medicine
import java.util.*
import kotlin.collections.ArrayList

class PageMedicineAdapter(private val c: Context,var list: ArrayList<Medicine> ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_medicine_drugs, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemModel = list[position]
        if (holder is ViewHolder) {
            val tv_drugs_name = holder.tv_drugs_name
            val tv_drugs_amount = holder.tv_drugs_amount
            tv_drugs_name.text = itemModel.medicineName
            tv_drugs_amount.text = itemModel.amount
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_drugs_name: TextView
    val tv_drugs_amount: TextView
    init {
        tv_drugs_name = itemView.findViewById(R.id.tv_drugs_name)
        tv_drugs_amount = itemView.findViewById(R.id.tv_drugs_amount)
    }
}
