package com.example.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.di.component.CoreComponent
import com.example.di.component.DaggerCoreComponent

class TestApp : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as TestApp).coreComponent
    }

}

fun Activity.coreComponent() = TestApp.coreComponent(this)
fun Fragment.coreComponent() = TestApp.coreComponent(requireContext())