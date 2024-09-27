package com.example.login

import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.base.util.ResManager
import com.example.login.databinding.FragmentLoginBinding
import com.example.common.R as baseR

class FillTextWatcher(
    private val viewBinding: FragmentLoginBinding,
    private val resManager: ResManager
) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(s: Editable?) {
        with(viewBinding) {
            Log.d("TEXT-WATCHER", "${emailEt.text}")
            if (emailEt.text?.isEmpty() == true) {
                if (continueBtn.isEnabled) {
                    continueBtn.isEnabled = false
                    continueBtn.backgroundTintList =
                        ColorStateList.valueOf(resManager.getColor(baseR.color.dark_blue))
                    continueBtn.setTextColor(resManager.getColor(baseR.color.grey4))
                }
            } else {
                if (!continueBtn.isEnabled) {
                    continueBtn.isEnabled = true
                    continueBtn.backgroundTintList =
                        ColorStateList.valueOf(resManager.getColor(baseR.color.blue))
                    continueBtn.setTextColor(resManager.getColor(baseR.color.white))
                }
            }
            emailTil.isErrorEnabled = false

        }
    }
}