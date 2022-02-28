package com.example.notesappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.notesappkotlin.R

class DialogFragment : DialogFragment() {

    var saveClick: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.customDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etText: EditText = view.findViewById(R.id.et_text)
        val tvSave: TextView = view.findViewById(R.id.tv_save)
        val tvCancel: TextView = view.findViewById(R.id.tv_cancel)

        tvSave.setOnClickListener {
            saveClick!!.invoke(etText.text.toString())
            dismiss()
        }

        tvCancel.setOnClickListener {
            dismiss()
        }
    }

}