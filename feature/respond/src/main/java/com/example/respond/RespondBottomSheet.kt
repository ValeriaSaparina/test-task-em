package com.example.respond

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.util.Constants.VACANCY_ID
import com.example.base.util.Constants.VACANCY_NAME
import com.example.coverletter.CoverLetterDialog
import com.example.respond.databinding.BottomSheetResponseBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.common.R as baseR

class RespondBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_response) {
    private val viewBinding: BottomSheetResponseBinding by viewBinding(BottomSheetResponseBinding::bind)

    private var vacancyId: String? = null
    private var vacancyName: String? = null

    override fun onStart() {
        super.onStart()
        setBackgroundColor()
    }

    private fun setBackgroundColor() {
        requireDialog().window?.apply {
            decorView.setBackgroundColor(
                resources.getColor(
                    baseR.color.dark_overlay,
                    context.theme
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vacancyId = arguments?.getString(VACANCY_ID) ?: ""
        vacancyName = arguments?.getString(VACANCY_NAME) ?: ""
        initListeners()
        initView()
    }

    private fun initView() {
        with(viewBinding) {
            companyNameTv.text = vacancyName
        }
    }

    private fun initListeners() {
        with(viewBinding) {
            coverLetterBtn.setOnClickListener {
                CoverLetterDialog.newInstance(vacancyId, vacancyName)
                    .show(parentFragmentManager, null)
                dismiss()
            }
            respBtn.setOnClickListener {
                dismiss()
            }
        }
    }
}