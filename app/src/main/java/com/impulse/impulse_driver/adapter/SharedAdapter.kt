package com.impulse.impulse_driver.adapter
import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.databinding.ItemHistoryBinding
import com.impulse.impulse_driver.databinding.ItemSharedAdapterBinding
import com.impulse.impulse_driver.model.SharedInfo
import java.util.*
import kotlin.collections.ArrayList

class SharedAdapter(val context: Context,private val subscriberList: List<SharedInfo>):BaseAdapter(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolders {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemSharedAdapterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_shared_adapter,parent,false)
        return SharedViewHolders(context,binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: SharedInfo = subscriberList[position]
        val itemViewHolder: SharedViewHolders = holder as SharedViewHolders
        itemViewHolder.bind(item)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }
}
class SharedViewHolders(var context: Context, val binding: ItemSharedAdapterBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: SharedInfo) {
        binding.apply {
            callStatus.setText(subscriber.callStatus)
        }
    }
}
