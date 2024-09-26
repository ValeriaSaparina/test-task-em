package com.example.base.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

class ResManager(
    private val ctx: Context
) {

    fun getString(@StringRes res: Int): String = ctx.resources.getString(res)

    fun getString(@StringRes res: Int, vararg args: Any?): String {
        return ctx.resources.getString(res, *args)
    }

    fun getColor(@ColorRes res: Int): Int = ctx.getColor(res)

    fun getDrawable(@DrawableRes res: Int): Drawable? = AppCompatResources.getDrawable(ctx, res)

}