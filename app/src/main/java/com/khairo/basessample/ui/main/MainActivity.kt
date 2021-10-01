package com.khairo.basessample.ui.main

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import com.khairo.bases.data.BaseActivity
import com.khairo.basessample.R
import com.khairo.basessample.databinding.ActivityMainBinding
import com.khairo.basessample.utils.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val dialogFragment: DialogFragment by lazy { com.khairo.basessample.ui.dialog.DialogFragment() }
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var navController: NavController

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        navController = supportFragmentManager.findNavController(viewId = R.id.main_nav_host)

    }

    companion object {
        lateinit var instance: MainActivity
    }
}
