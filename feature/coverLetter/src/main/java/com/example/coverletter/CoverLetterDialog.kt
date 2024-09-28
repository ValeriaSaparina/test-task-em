package com.example.coverletter

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.util.Constants.VACANCY_ID
import com.example.base.util.Constants.VACANCY_NAME
import com.example.coverletter.databinding.DialogCoverLetterBinding
import com.example.common.R as baseR

class CoverLetterDialog : DialogFragment(R.layout.dialog_cover_letter) {

    private val viewBinding: DialogCoverLetterBinding by viewBinding(DialogCoverLetterBinding::bind)

    override fun onStart() {
        super.onStart()
        setBackgroundColor()
        setDialogLayoutSize()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initListeners()
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
        setStyle(STYLE_NORMAL, com.google.android.material.R.style.AlertDialog_AppCompat)
    }

    private fun setDialogLayoutSize() {
        dialog?.window?.run {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setGravity(Gravity.CENTER)
        }
    }

    private fun setData() {
        with(viewBinding) {
            companyNameTv.text = arguments?.getString(VACANCY_NAME) ?: ""
        }
    }

    private fun initListeners() {
        with(viewBinding) {
            root.setOnClickListener {
                dismiss()
            }
            contentLl.setOnClickListener {}
            respondBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    companion object {
        fun newInstance(vacancyId: String?, vacancyName: String?): CoverLetterDialog {
            return CoverLetterDialog().apply {
                arguments = Bundle().apply {
                    putString(VACANCY_ID, vacancyId)
                    putString(VACANCY_NAME, vacancyName)
                }
            }
        }
    }
}