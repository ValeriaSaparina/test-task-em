package com.example.testtask

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.base.base.BaseActivity
import com.example.common.R as commonR

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as NavHostFragment
        controller = navHostFragment.navController

        bottomNav.itemActiveIndicatorColor =
            ColorStateList.valueOf(resources.getColor(commonR.color.black, theme))

        controller.navigate(com.example.navigation.R.id.loginFragment)
    }

}