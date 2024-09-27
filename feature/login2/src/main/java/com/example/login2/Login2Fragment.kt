package com.example.login2

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseFragment
import com.example.base.util.Constants.EMAIL_ID
import com.example.di.coreComponent
import com.example.login.di.DaggerLogin2Component
import com.example.login2.databinding.FragmentLogin2Binding
import com.example.presentation.viewmodel.Login2ViewModel
import javax.inject.Inject
import com.example.common.R as baseR
import com.example.navigation.R as navR

class Login2Fragment : BaseFragment(R.layout.fragment_login2) {

    private val viewBinding: FragmentLogin2Binding by viewBinding(FragmentLogin2Binding::bind)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: Login2ViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerLogin2Component.factory().create(coreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.firstNumberEt.requestFocus()
        viewBinding.emailTv.text =
            resources.getString(baseR.string.send_code, arguments?.getString(EMAIL_ID))
        initListeners()
    }

    private fun initListeners() {
        with(viewBinding) {
            firstNumberEt.addTextChangedListener(
                com.example.base.util.whatcher.NextFocusTextWatcher(
                    nextEditText = secondNumberEt,
                    setButtonState = ::setButtonState
                )
            )
            secondNumberEt.addTextChangedListener(
                com.example.base.util.whatcher.NextFocusTextWatcher(
                    previousEditText = firstNumberEt,
                    nextEditText = thirdNumberEt,
                    setButtonState = ::setButtonState
                )
            )
            thirdNumberEt.addTextChangedListener(
                com.example.base.util.whatcher.NextFocusTextWatcher(
                    previousEditText = secondNumberEt,
                    nextEditText = fourthNumberEt,
                    setButtonState = ::setButtonState
                )
            )
            fourthNumberEt.addTextChangedListener(
                com.example.base.util.whatcher.NextFocusTextWatcher(
                    previousEditText = thirdNumberEt,
                    setButtonState = ::setButtonState
                )
            )

            confirmBtn.setOnClickListener {
                findNavController().navigate(navR.id.action_login2Fragment_to_mainFragment)
            }
        }
    }

    private fun setButtonState() {
        with(viewBinding) {
            val code = firstNumberEt.text.toString() +
                    secondNumberEt.text.toString() +
                    thirdNumberEt.text.toString() +
                    fourthNumberEt.text.toString()

            with(confirmBtn) {
                if (viewModel.isValidCode(code)) {
                    isEnabled = true
                    backgroundTintList =
                        ColorStateList.valueOf(requireContext().getColor(baseR.color.blue))
                    setTextColor(requireContext().getColor(baseR.color.white))
                } else {
                    isEnabled = false
                    backgroundTintList =
                        ColorStateList.valueOf(requireContext().getColor(baseR.color.dark_blue))
                    setTextColor(requireContext().getColor(baseR.color.grey4))

                }
            }
        }
    }


}