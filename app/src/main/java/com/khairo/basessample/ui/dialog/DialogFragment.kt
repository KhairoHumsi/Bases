package com.khairo.basessample.ui.dialog

import com.khairo.bases.data.BaseDialogFragment
import com.khairo.basessample.R
import com.khairo.basessample.databinding.FragmentDialogBinding

class DialogFragment : BaseDialogFragment<FragmentDialogBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_dialog

    override fun setUpViewAndActions() { }
}
