package com.example.base.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.base.util.observe
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

open class BaseFragment(@LayoutRes fragment: Int) : Fragment(fragment) {

    inline fun <T> Flow<T>.observe(crossinline block: suspend (T) -> Unit): Job {
        return observe(fragment = this@BaseFragment, block)
    }

    protected fun showFavNumberBadge(numberFav: Int) {
        (requireActivity() as BaseActivity).isBadgeFavoriteVisible = numberFav != 0
        (requireActivity() as BaseActivity).badgeNumber = numberFav

    }

    protected fun showFavNumberBadge() {
         if ((requireActivity() as BaseActivity).badgeNumber != 0) {
             (requireActivity() as BaseActivity).isBadgeFavoriteVisible = true
         }
    }

    protected fun updateFavBadge(isAdded: Boolean) {
        val prevNumber = (requireActivity() as BaseActivity).badgeNumber
        if (isAdded) {
            showFavNumberBadge(prevNumber + 1)
        } else {
            showFavNumberBadge(prevNumber - 1)
        }
    }

}