package com.room.fashion.view

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.room.fashion.Base.BaseActivity
import com.room.fashion.R
import com.room.fashion.databinding.ActivityMainBinding
import com.room.fashion.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initNavigationBar() {
        val navController = findNavController(R.id.main_nav_host)

        binding.bottomNavi.setupWithNavController(navController)
    }

}