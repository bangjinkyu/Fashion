package com.room.fashion.view.favorite

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.room.fashion.MainViewModel
import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.databinding.FragmentFavoriteBinding
import com.room.fashion.model.FashionResponse
import com.room.fashion.util.OnItemClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment() : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    private val fashionRecyclerViewAdapter: FashionListAdapter by inject()

     private val mainViewModel: MainViewModel by sharedViewModel()

    override val viewModel: FavoriteViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_favorite

    override fun initStartView() {
        binding.favoriteRecycler.run {
            fashionRecyclerViewAdapter.imageItemList.clear()
            adapter = fashionRecyclerViewAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }
    }

    override fun subscribeObservers() {
        viewModel.fashionGoodLiveData.observe(this, Observer {
            fashionRecyclerViewAdapter.submitList(it)
        })
    }

    override fun initDataBinding() {
        val chartData = mainViewModel.getShareLiveData.value?.filter { it.isFavorite }
        viewModel.getFashionFavorite(chartData!!)
    }

    override fun initAfterBinding() {
        fashionRecyclerViewAdapter.setOnItemClickListener(
            object : OnItemClickListener {
                override fun onItemClick(
                    holder: FashionListAdapter.ImageHolder,
                    view: View,
                    position: Int
                ) {
                   // TODO("Not yet implemented")
                }

                override fun onBannerItemClicked(bannerItem: FashionResponse.FashionBanner) {
                    //TODO("Not yet implemented")
                }
            }
        )
    }
}