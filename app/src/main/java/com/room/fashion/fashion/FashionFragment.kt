package com.room.fashion.fashion

import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.databinding.FragmentFashionBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FashionFragment: BaseFragment<FragmentFashionBinding, FashionViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_fashion

    override val viewModel: FashionViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {
    }

    override fun subscribeObservers() {
    }

    override fun initAfterBinding() {
    }
}