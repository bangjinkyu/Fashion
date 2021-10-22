package com.room.fashion.view.favorite

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.room.fashion.SharedViewModel
import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.databinding.FragmentFavoriteBinding
import com.room.fashion.model.FashionResponse
import com.room.fashion.util.OnItemClickListener
import com.room.fashion.view.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment() : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    @Inject
    lateinit var fashionRecyclerViewAdapter: FashionListAdapter

     private val mainViewModel: SharedViewModel by activityViewModels()

    private val homeViewModel: HomeViewModel by activityViewModels()

    override val viewModel: FavoriteViewModel by viewModels()

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
        val chartData = mainViewModel.shareLiveData.filter { it.isFavorite }
        viewModel.getFashionFavorite(chartData)
    }

    override fun onEvent() {

    }
}