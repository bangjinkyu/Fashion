package com.room.fashion

import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.room.fashion.base.BaseActivity
import com.room.fashion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, SharedViewModel>() {

    override val viewModel: SharedViewModel by viewModels()

    override val layoutId: Int
    get() = R.layout.activity_main

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }


    override fun initNavigationBar() {
        val navController = findNavController(R.id.main_nav_host)

        binding.bottomNavi.setupWithNavController(navController)
    }
}