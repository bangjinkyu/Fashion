package com.room.fashion.view

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.room.fashion.Base.BaseFragment
import com.room.fashion.R
import com.room.fashion.databinding.FragmentHomeBinding
import com.room.fashion.model.response.FashionResponse
import com.room.fashion.viewmodel.HomeViewModel
import com.room.fashion.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kr.lazynight.android.adapter.FashionListAdapter
import kr.lazynight.android.adapter.ViewPagerAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private  val viewPagerAdapter: ViewPagerAdapter by inject()

    private val fashionRecyclerViewAdapter: FashionListAdapter by inject()

    override val viewModel: HomeViewModel by viewModel()

    private val mainViewModel: MainViewModel by sharedViewModel()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initStartView() {
        binding.homeSearchRecycler.run {
            adapter = fashionRecyclerViewAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

    }

    override fun initViewPager2() {
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

    }


    override fun subscribeObservers() {
        viewModel.fashionGoodLiveData.observe(viewLifecycleOwner, Observer {
            fashionRecyclerViewAdapter.submitList(it)
            mainViewModel.setLiveData(it)
        })

        viewModel.bannerItemList.observe(viewLifecycleOwner, Observer {
            viewPagerAdapter.submitList(it)
        })
        viewModel.currentPosition.observe(viewLifecycleOwner, Observer {
            binding.viewPager2.currentItem = it
        })
    }

    override fun initAfterBinding() {
        viewModel.getBannerItems()
        viewModel.getFashionSearch()

        binding.mainActivitySearchButton.setOnClickListener {
          // To do
        }

        fashionRecyclerViewAdapter.setOnItemClickListener(
            object : OnItemClickListener{
                override fun onBannerItemClicked(bannerItem: FashionResponse.FashionBanner) {
                    TODO("Not yet implemented")
                }
                override fun onItemClick(
                    holder: FashionListAdapter.ImageHolder,
                    view: View,
                    position: Int
                ) {
                    mainViewModel.getLiveData()?.get(position)?.let {
                        if (!it.isFavorite)  {
                            it.isFavorite =  true
                            view.background = context?.getDrawable(R.drawable.favorite_selected)
                        } else {
                            it.isFavorite =  false
                            view.background = context?.getDrawable(R.drawable.favorite_normal)
                        }
                    }
                }
            }
        )
    }

    override fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (viewLifecycleOwner.lifecycleScope.isActive) {
                delay(2000)
                viewModel.getCurrentPosition()?.let {
                    viewModel.setCurrentPosition(it.plus(1) % 3)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

}