package com.room.fashion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.room.fashion.databinding.ItemFashionAdapterBinding

class FashionListViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    data class ImageItem(var imageUrl:String)

    private val imageItemList = ArrayList<ImageItem>()

    override fun getItemCount() = imageItemList.size

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
        (holder as? ImageHolder)?.onBind(imageItemList[position])
    }

    inner class ImageHolder(private val binding: ItemFashionAdapterBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(item: ImageItem) {
            itemView.run {
                Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.itemImageView)
            }
        }
    }

    fun addImageItem(imageUrl: String){
        imageItemList.add(ImageItem(imageUrl))
    }
}