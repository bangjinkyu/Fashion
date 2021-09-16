package com.room.fashion.view

import com.room.fashion.Base.BaseFragment
import com.room.fashion.R
import com.room.fashion.databinding.FragmentFashionBinding
import com.room.fashion.viewmodel.FashionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FashionFragment: BaseFragment<FragmentFashionBinding, FashionViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_fashion

    override val viewModel: FashionViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initViewPager2() {
    }

    override fun initDataBinding() {
    }

    override fun autoScrollViewPager() {
    }

    override fun subscribeObservers() {
    }

    override fun initAfterBinding() {
    }
}