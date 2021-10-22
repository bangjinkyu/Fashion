package kr.lazynight.android.view.search

import androidx.fragment.app.viewModels
import com.room.fashion.R
import com.room.fashion.base.BaseFragment
import com.room.fashion.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_search

    override val viewModel: SearchViewModel by viewModels()

    override fun initStartView() {

    }

    override fun initDataBinding() {
    }


    override fun subscribeObservers() {
    }

    override fun onEvent() {
    }
}