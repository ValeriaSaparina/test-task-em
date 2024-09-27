package com.example.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseFragment
import com.example.base.util.Constants.EMAIL_ID
import com.example.base.util.ResManager
import com.example.di.coreComponent
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.di.DaggerLoginComponent
import com.example.presentation.viewmodel.LoginViewModel
import javax.inject.Inject
import com.example.common.R as baseR
import com.example.navigation.R as navR

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerLoginComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(viewBinding) {
            emailEt.addTextChangedListener(FillTextWatcher(this, ResManager(requireContext())))

            continueBtn.setOnClickListener {
                viewModel.login(emailEt.text.toString())
            }
        }
    }

    private fun initObservers() {
        with(viewModel) {

            success.observe { success ->
                if (success) {
                    findNavController().navigate(
                        navR.id.action_loginFragment_to_login2Fragment,
                        Bundle().apply {
                            putString(EMAIL_ID, viewBinding.emailEt.text.toString())
                        })
                }
            }

            error.observe { err ->
                if (err) {
                    with(viewBinding) {
                        emailTil.error = resources.getString(baseR.string.invalid_email)
                    }
                    clearResults()
                }
            }

        }
    }
}