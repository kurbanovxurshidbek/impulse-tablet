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
import java.util.*

class HistoryAdapter(val context: Context,private val clickListener:(BaseMedicine)->Unit):BaseAdapter(){
    private val subscriberList= ArrayList<BaseMedicine>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolders {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemHistoryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_history,parent,false)
        return HistoryViewHolders(context,binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: BaseMedicine = subscriberList[position]
        val itemViewHolder: HistoryViewHolders = holder as HistoryViewHolders
        itemViewHolder.bind(item,clickListener)
    }
    fun setList(subscriber: List<BaseMedicine>) {
        subscriberList.clear()
        subscriberList.addAll(subscriber)
    }


    override fun getItemCount(): Int {
        return subscriberList.size
    }
}
class HistoryViewHolders(var context: Context, val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: BaseMedicine, clickListener: (BaseMedicine) -> Unit) {
        binding.apply {
            ptName.text = subscriber.fullName
            tvAddress.text = subscriber.street
            tvCardNumber.text = subscriber.cardNumber
            tvAge.text = subscriber.age
            tvHeight.text = subscriber.height.toString()
            tvWeight.text = subscriber.weight.toString()
            tvCurrentDate.text = subscriber.currentDate
            tvStatusInfo.text = subscriber.callStatus
            Glide.with(context).load(subscriber.patientImg).placeholder(R.drawable.user)
                .error(R.drawable.cancel).into(ptImg)

            llItem.setOnClickListener {
                clickListener(subscriber)
            }
            lottieAnimationClick.setAnimation("clickB.json")
            countDownTimer()
        }
    }

    private fun countDownTimer() {
        object : CountDownTimer(4000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                binding.apply {
                    lottieAnimationClick.visibility = View.GONE
                }

            }
        }.start()
    }
}
