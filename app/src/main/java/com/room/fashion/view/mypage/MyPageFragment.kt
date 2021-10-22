package com.room.fashion.view.mypage

import androidx.fragment.app.viewModels
import com.room.fashion.base.BaseFragment
import com.room.fashion.R
import com.room.fashion.databinding.FragmentFashionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment: BaseFragment<FragmentFashionBinding, MyPageViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_fashion

    override val viewModel: MyPageViewModel by viewModels()

    override fun initStartView() {

    }

    override fun initDataBinding() {
    }

    override fun subscribeObservers() {
    }

    override fun onEvent() {
    }
}