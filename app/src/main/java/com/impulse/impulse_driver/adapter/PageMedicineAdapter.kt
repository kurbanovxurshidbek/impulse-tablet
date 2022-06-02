package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.database.AppDatabase
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.databinding.ItemMedicineDrugsBinding
import com.impulse.impulse_driver.manager.RoomManager
import com.impulse.impulse_driver.model.Medicine
import java.util.*
import kotlin.collections.ArrayList

class PageMedicineAdapter : RecyclerView.Adapter<PageMedicineAdapter.Vh>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)
    var deleteClick: ((Medicine) -> Unit)? =  null

    inner class Vh(var binding: ItemMedicineDrugsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val medicine = dif.currentList[adapterPosition]
            binding.tvDrugsAmount.text = medicine.amount

            binding.deleteItem.setOnClickListener{
                deleteClick?.invoke(medicine)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemMedicineDrugsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun submitList(list: List<Medicine>) {
        dif.submitList(list)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Medicine>() {
            override fun areItemsTheSame(
                oldItem: Medicine,
                newItem: Medicine
            ): Boolean {
                return true
            }

            override fun areContentsTheSame(
                oldItem: Medicine,
                newItem: Medicine
            ): Boolean {
                return true
            }


        }
    }
}
