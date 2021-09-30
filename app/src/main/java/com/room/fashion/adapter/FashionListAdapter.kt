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

    var imageItemList: List<FashionResponse.FashionGood>? = null

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
            binding.tvTitle.text = item.name
            val dis = 1 - (item.price.toFloat() / item.actualPrice.toFloat())
            if (dis != 0.0f ) {
                binding.tvDiscount.text = dis.formatPercent()
                binding.tvDiscount.visibility = View.VISIBLE
            }

            binding.tvPrice.text = item.price.formatComma()
            binding.tvCount.text = item.sellCount.toString()
            if (item.isNew) {
                binding.imageNew.visibility = View.VISIBLE
            }

            binding.favorView.background = if (item.isFavorite) {
                binding.root.context.getDrawable(R.drawable.favorite_selected)
            } else {
                binding.root.context.getDrawable(R.drawable.favorite_normal)
            }


            itemView.run {
                Glide.with(context)
                    .load(item.image)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.itemImageView)
            }
        }
    }

    fun submitList(list: List<FashionResponse.FashionGood>?) {
        imageItemList = list
        notifyDataSetChanged()
    }
}