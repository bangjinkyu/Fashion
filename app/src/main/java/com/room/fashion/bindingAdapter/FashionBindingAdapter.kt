package kr.lazynight.android.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.room.fashion.R

object FashionBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        // Glide 사용 하기
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
    }

    @BindingAdapter("favoImage")
    @JvmStatic
    fun ImageView.favoriteImage(isFavorite: Boolean) {
        when(isFavorite) {
            true  ->  setBackgroundResource(R.drawable.favorite_selected)
            false ->  setBackgroundResource(R.drawable.favorite_normal)
        }
    }
}