package com.impulse.impulse_driver.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ItemMedicineDrugsBinding
import com.impulse.impulse_driver.model.Medicine
import java.util.*

class PageMedicineAdapter(private val clickListener:(Medicine)->Unit):RecyclerView.Adapter<MyViewHolder>(){
    private val subscriberList= ArrayList<Medicine>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMedicineDrugsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_medicine_drugs,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position],clickListener)
    }
    fun setList(subscriber: List<Medicine>) {
        subscriberList.clear()
        subscriberList.addAll(subscriber)
    }


    override fun getItemCount(): Int {
        return subscriberList.size
    }
}
class MyViewHolder(val binding: ItemMedicineDrugsBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: Medicine, clickListener: (Medicine) -> Unit) {
        binding.apply {
            tvDrugsName.text = subscriber.name
            tvDrugsAmount.text = subscriber.amount.toString()
            binding.listItemLayout.setOnClickListener {
                clickListener(subscriber)
            }
        }
    }
}
