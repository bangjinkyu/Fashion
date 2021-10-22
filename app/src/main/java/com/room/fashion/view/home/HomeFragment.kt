package com.room.fashion.view.home

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.adapter.FashionListAdapter
import com.room.fashion.adapter.ViewPagerAdapter
import com.room.fashion.databinding.FragmentHomeBinding
import com.room.fashion.SharedViewModel
import com.room.fashion.extensions.getFromDrawable
import com.room.fashion.model.FashionGoods
import com.room.fashion.util.ApiResult
import com.room.fashion.util.PagingRecyclerViewCallback
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @Inject
    lateinit  var viewPagerAdapter: ViewPagerAdapter

    @Inject
    lateinit var fashionListAdapter: FashionListAdapter

    override val viewModel: HomeViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var time = 0

    private var timerTask : Timer? = null

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
                    binding.position = position
                    viewModel.setCurrentPosition(position)
                }
            })
        }

        fashionListAdapter.setOnItemClickListener(onSelected =  { view, position->
            sharedViewModel.shareLiveData.get(position).let {
                if (!it.isFavorite) {
                    it.isFavorite = true
                    view.background = requireContext().getFromDrawable(R.drawable.favorite_selected)
                } else {
                    it.isFavorite = false
                    view.background = requireContext().getFromDrawable(R.drawable.favorite_normal)
                }
            }
        })
    }

    override fun initDataBinding() {
        viewModel.getBannerItems()
        viewModel.getFashionSearch()
    }


    override fun subscribeObservers() {
        viewModel.fashionGoodLiveData.observe(viewLifecycleOwner, {
            fashionListAdapter.submitList(it)
            sharedViewModel.setLiveData(it)
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

    private fun displayResult(result: ApiResult<FashionGoods>) {
        when(result) {
            is ApiResult.Loading -> {
                Log.d("room", "Result.Loading")
            }
            is ApiResult.Success -> {
                Log.d("room", "Result.Success")
                binding.isProgress = true
            }
            else  -> {
                binding.isProgress = false
            }
        }
    }

    override fun onEvent() {
        startBanner()
    }

    private fun startBanner() {
        timerTask = timer(period = 2000) {
            viewModel.getCurrentPosition()?.let {
                viewModel.setCurrentPosition(it.plus(1) % 3)
            }
        }
    }
}