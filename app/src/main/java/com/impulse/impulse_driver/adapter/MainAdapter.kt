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
import com.impulse.impulse_driver.databinding.ItemMainHeaderBinding
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

import java.util.*

class MainAdapter(val context: Context): BaseAdapter(){
    private val subscriberList= ArrayList<PatientInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMainHeaderBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_main_header,parent,false)
        return MainViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: PatientInfo = subscriberList[position]
        val itemViewHolder: MainViewHolder = holder as MainViewHolder
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
 class MainViewHolder(var context: Context,val binding: ItemMainHeaderBinding) : RecyclerView.ViewHolder(binding.root){
    private  var subscriberViewModel: SubscriberViewModel? = null
    fun bind(subscriber: PatientInfo) {
        binding.apply {
            Glide.with(context).load(subscriber.patientImg).placeholder(R.drawable.user)
                .error(R.drawable.cancel).into(ptImg)
            ptName.text = subscriber.fullName
            tvAddress.text = subscriber.addressName
            tvStatusInfo.text = subscriber.callStatus
            tvAge.text = subscriber.age.toString()
            tvHeight.text = subscriber.height.toString()
            tvWeight.text = subscriber.weight.toString()
            tvCardNumber.text = subscriber.cardNumber.toString()
            tvBloodGroup.text = subscriber.bloodGroup.toString()
            val dao = MedicineDatabase.getInstance(context.applicationContext).subscriberDao
            val repository = MedicineRepository(dao)
            val factory = SubscriberViewModelFactory(repository)

        }
    }
}
