package com.khairo.basessample

import android.os.Bundle
import com.khairo.bases.data.BaseDialogFragment
import com.khairo.basessample.databinding.FragmentDialogBinding

class DialogFragment : BaseDialogFragment<FragmentDialogBinding>(R.layout.fragment_dialog) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
