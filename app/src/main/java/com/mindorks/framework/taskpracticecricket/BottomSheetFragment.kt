package com.mindorks.framework.taskpracticecricket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mindorks.framework.taskpracticecricket.databinding.FragmentDetailsBinding
import com.mindorks.framework.taskpracticecricket.model2.Player

class BottomSheetFragment(val player: Player) :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.player = player

        binding.dismiss.setOnClickListener(){
            dismiss()
        }
    }

}