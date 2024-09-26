package com.example.base.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.common.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.navigation.R as navR

open class BaseActivity : AppCompatActivity() {

    protected lateinit var bottomNav: BottomNavigationView
    var isBottomNavVisible: Boolean
        get() = bottomNav.isVisible
        set(value) {
            bottomNav.isVisible = value
        }

    var isBadgeFavoriteVisible: Boolean
        get() = bottomNav.getOrCreateBadge(navR.id.favoriteFragment).isVisible
        set(isVisible) {
            bottomNav.getOrCreateBadge(navR.id.favoriteFragment).isVisible = isVisible
        }

    var badgeNumber: Int
        get() = bottomNav.getOrCreateBadge(navR.id.favoriteFragment).number
        set(number) {
            bottomNav.getOrCreateBadge(navR.id.favoriteFragment).number = number
        }

    protected lateinit var controller: NavController


    fun setupBottomNav() {
        bottomNav.setupWithNavController(controller)
        bottomNav.getOrCreateBadge(navR.id.favoriteFragment).run {
            backgroundColor = resources.getColor(R.color.red, theme)
            badgeTextColor = resources.getColor(R.color.white, theme)
            isVisible = false
        }

    }

}