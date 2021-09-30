package com.room.fashion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.room.fashion.R
import com.room.fashion.databinding.ItemLayoutBannerBinding
import com.room.fashion.model.FashionResponse

class ViewPagerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bannerItemList: List<FashionResponse.FashionBanner>? = null

    override fun getItemCount(): Int {
        return bannerItemList?.size ?: 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemLayoutBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList?.let { bannerItemList ->
            (holder as BannerViewHolder).bind(bannerItemList[position])
        }
    }

    //ViewHolder
    class BannerViewHolder(
        private val binding: ItemLayoutBannerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bannerItem: FashionResponse.FashionBanner) {
            binding.ivBannerImage.setOnClickListener {
               // interaction.onBannerItemClicked(bannerItem)
            }
            Glide.with(itemView)
                .load(bannerItem.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivBannerImage)
            //binding.ivBannerImage.setImageResource(bannerItem.image)
        }
    }

    //functions
    fun submitList(list: List<FashionResponse.FashionBanner>?) {
        bannerItemList = list
        notifyDataSetChanged()
    }
}