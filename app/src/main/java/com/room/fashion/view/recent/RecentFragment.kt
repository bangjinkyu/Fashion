package kr.lazynight.android.view.recent

import androidx.fragment.app.viewModels
import com.room.fashion.R
import com.room.fashion.base.BaseFragment
import com.room.fashion.databinding.FragmentRecentBinding

class RecentFragment: BaseFragment<FragmentRecentBinding, RecentViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_recent

    override val viewModel: RecentViewModel by viewModels()

    override fun initStartView() {

    }

    override fun initDataBinding() {
    }


    override fun subscribeObservers() {
    }

    override fun onEvent() {
    }
}