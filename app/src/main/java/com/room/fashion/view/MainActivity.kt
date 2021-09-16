package com.room.fashion.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.room.fashion.Base.BaseActivity
import com.room.fashion.FashionListViewAdapter
import com.room.fashion.R
import com.room.fashion.databinding.ActivityMainBinding
import com.room.fashion.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    private val fashionListViewAdapter: FashionListViewAdapter by inject()

    //private val fragmentOne by lazy { MovieListFragment() }


    override fun initStartView() {
        viewDataBinding.mainActivitySearchRecyclerView.run {
            adapter = fashionListViewAdapter
            layoutManager = StaggeredGridLayoutManager(3, 1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.fashionResponseLiveData.observe(this, Observer {
            it.goods.forEach { good ->
                fashionListViewAdapter.addImageItem(good.image)
            }
            fashionListViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        viewDataBinding.mainActivitySearchButton.setOnClickListener {
            viewModel.getFashionSearch(viewDataBinding.mainActivitySearchTextView.text.toString().toInt())
        }
    }



    override fun initNavigationBar() {
        bottom_navigation.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                   // R.id.tab1 -> { changeFragment(fragmentOne) }
                    R.id.tab2 -> {  }
                    R.id.tab3 -> {  }
                }
                true
            }
            selectedItemId = R.id.tab1
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}