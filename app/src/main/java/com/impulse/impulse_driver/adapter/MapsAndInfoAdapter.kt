package com.impulse.impulse_driver.adapter



import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ItemMapsAndInfoBinding
import com.impulse.impulse_driver.model.PatientInfo
import java.util.*

class MapsAndInfoAdapter(val context: Context): BaseAdapter(){
    private val subscriberList= ArrayList<PatientInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsAndInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMapsAndInfoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_maps_and_info,parent,false)
        return MapsAndInfoViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: PatientInfo = subscriberList[position]
        val itemViewHolder: MapsAndInfoViewHolder = holder as MapsAndInfoViewHolder
        itemViewHolder.bind(item)
    }
    fun setList(subscriber: List<PatientInfo>) {
        subscriberList.clear()
        subscriberList.addAll(subscriber)
    }


    override fun getItemCount(): Int {
        return subscriberList.size
    }

   inner class MapsAndInfoViewHolder(val context: Context,val binding: ItemMapsAndInfoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(subscriber: PatientInfo) {
            binding.apply {
                ptName.text = subscriber.fullName
                tvAddress.text = subscriber.street
                tvStatusInfo.text = subscriber.callStatus
                tvCall.text = subscriber.phoneNumber.toString()

                /** openContact with your device**/

                var tv_call = tvCall.text.toString()
                openContact.setOnClickListener {
                    openCallContact(tv_call,context)
                }
            }
        }

        private fun openCallContact(tvCall: String, context: Context) {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Uri.encode(tvCall)))
            context.startActivity(callIntent)
        }

    }
}
