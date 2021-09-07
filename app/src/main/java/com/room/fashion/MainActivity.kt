package com.room.fashion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.room.fashion.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fragmentOne by lazy { MovieListFragment() }
    private val fragmentTwo by lazy { MovieFavoriteListFragment() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        initNavigationBar()
    }



    private fun initNavigationBar() {
        bottom_navigation.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.tab1 -> { changeFragment(fragmentOne) }
                    R.id.tab2 -> { changeFragment(fragmentTwo) }
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