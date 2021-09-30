package com.room.fashion

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.room.fashion.base.BaseActivity
import com.room.fashion.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int
    get() = R.layout.activity_main

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