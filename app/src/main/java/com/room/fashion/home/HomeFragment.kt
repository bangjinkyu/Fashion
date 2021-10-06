package com.room.fashion.home

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.adapter.ViewPagerAdapter
import com.room.fashion.databinding.FragmentHomeBinding
import com.room.fashion.model.FashionResponse
import com.room.fashion.util.OnItemClickListener
import com.room.fashion.MainViewModel
import com.room.fashion.model.FashionGoods
import com.room.fashion.util.PagingRecyclerViewCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private  val viewPagerAdapter: ViewPagerAdapter by inject()

    private val fashionListAdapter: FashionListAdapter by inject()

    override val viewModel: HomeViewModel by viewModel()

    private val mainViewModel: MainViewModel by sharedViewModel()

    private val pagingRecyclerViewCallback by lazy {
        PagingRecyclerViewCallback{ pageIndex ->
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getFashionPage(pageIndex * 10)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home


    override fun initStartView() {
        binding.homeSearchRecycler.apply {
            adapter = fashionListAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            addOnScrollListener(pagingRecyclerViewCallback)
        }

        binding.viewPager2.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvPageNumber.text = "${position + 1}"
                    viewModel.setCurrentPosition(position)
                }
            })
        }
    }

    override fun initDataBinding() {
        viewModel.getBannerItems()
        viewModel.getFashionSearch()
    }


    override fun subscribeObservers() {
        viewModel.fashionGoodLiveData.observe(viewLifecycleOwner, {
            fashionListAdapter.submitList(it)
            mainViewModel.setLiveData(it)
        })

        viewModel.bannerItemList.observe(viewLifecycleOwner,  {
            viewPagerAdapter.submitList(it)
        })
        viewModel.currentPosition.observe(viewLifecycleOwner,  {
            binding.viewPager2.currentItem = it
        })

        viewModel.resultLiveData.observe(viewLifecycleOwner, {
            displayResult(it)
        })
    }

    private fun displayResult(result: Result<FashionGoods>) {
        when(result) {
            is Result.Success -> {
                Log.d("room", "Result.Success")
                binding.progressBar.visibility = View.GONE
            }
            is Result.Error -> {

            }
        }
    }

    override fun initAfterBinding() {
        fashionListAdapter.setOnItemClickListener(
            object : OnItemClickListener {
                override fun onBannerItemClicked(bannerItem: FashionResponse.FashionBanner) {
                    TODO("Not yet implemented")
                }
                override fun onItemClick(
                    holder: FashionListAdapter.ImageHolder,
                    view: View,
                    position: Int
                ) {
                    mainViewModel.getShareLiveData.observe(viewLifecycleOwner, {
                        it.get(position).let {
                            if (!it.isFavorite) {
                                it.isFavorite = true
                                view.background = context?.getDrawable(R.drawable.favorite_selected)
                            } else {
                                it.isFavorite = false
                                view.background = context?.getDrawable(R.drawable.favorite_normal)
                            }
                        }
                    })
                }
            }
        )

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (viewLifecycleOwner.lifecycleScope.isActive) {
                delay(2000)
                viewModel.getCurrentPosition()?.let {
                    viewModel.setCurrentPosition(it.plus(1) % 3)
                }
            }
        }
    }
}