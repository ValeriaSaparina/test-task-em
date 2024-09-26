package com.example.base.util.whatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class NextFocusTextWatcher(
    private val previousEditText: EditText? = null,
    private val nextEditText: EditText? = null,
    private val setButtonState: () -> Unit,
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().isEmpty()) {
            previousEditText?.requestFocus()
        } else {
            nextEditText?.requestFocus()
        }
        setButtonState()
    }
}