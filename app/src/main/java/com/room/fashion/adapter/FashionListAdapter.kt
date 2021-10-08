package com.room.fashion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.room.fashion.R
import com.room.fashion.databinding.ItemFashionAdapterBinding
import com.room.fashion.extensions.formatComma
import com.room.fashion.extensions.formatPercent
import com.room.fashion.model.FashionResponse
import com.room.fashion.util.OnItemClickListener

class FashionListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.mListener = listener
    }

    var imageItemList = mutableListOf<FashionResponse.FashionGood>()

    override fun getItemCount() = imageItemList?.size ?: 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder{
        val binding = ItemFashionAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int)
    {
        imageItemList?.let {
            (holder as? ImageHolder)?.onBind(it[position])
        }
    }

     inner class ImageHolder(private val binding: ItemFashionAdapterBinding): RecyclerView.ViewHolder(
         binding.root
    ) {
         init {
             itemView.setOnClickListener {
                 mListener?.onItemClick(this, binding.favorView, adapterPosition)
             }
         }

        fun onBind(item: FashionResponse.FashionGood) {
            binding.mydata = item
            binding.isSale = false
            val dis = 1 - (item.price.toFloat() / item.actualPrice.toFloat())
            if (dis != 0.0f ) {
                binding.tvDiscount.text = dis.formatPercent()
                binding.isSale = true
            }
        }
    }

    fun submitList(list: List<FashionResponse.FashionGood>) {
        imageItemList.addAll(list)
        notifyDataSetChanged()
    }
}