package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.ItemAmbulanceViewBinding
import com.impulse.impulse_driver.databinding.ItemMainHeaderBinding
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

import java.util.*

class AmbulanceAdapter(val context: Context): BaseAdapter(){
    private val subscriberList= ArrayList<PatientInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemAmbulanceViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_ambulance_view,parent,false)
        return AmbulanceViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: PatientInfo = subscriberList[position]
        val itemViewHolder: AmbulanceViewHolder = holder as AmbulanceViewHolder
        itemViewHolder.bind(item)
    }
    fun setList(subscriber: List<PatientInfo>) {
        subscriberList.clear()
        subscriberList.addAll(subscriber)
    }


    override fun getItemCount(): Int {
        return subscriberList.size
    }
}
 class AmbulanceViewHolder(var context: Context,val binding: ItemAmbulanceViewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: PatientInfo) {
        binding.apply {
            ptName.text = subscriber.fullName
            tvAddress.text = subscriber.street
            tvStatusInfo.text = subscriber.callStatus
            tvAge.text = subscriber.age.toString()

        }
    }
}
