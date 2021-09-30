package com.room.fashion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment <B : ViewDataBinding, V : ViewModel>  : Fragment() {

    lateinit var binding: B

    abstract val viewModel: V

    abstract val layoutId: Int

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    abstract fun subscribeObservers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this@BaseFragment

        initStartView()
        initDataBinding()
        initAfterBinding()
        subscribeObservers()
    }

    protected fun showToast(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

}