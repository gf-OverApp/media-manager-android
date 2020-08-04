package com.overapp.videoplayerdemo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.overapp.videoplayerdemo.R
import kotlinx.android.synthetic.main.fragment_selection.*

class SelectionFragment() : Fragment(R.layout.fragment_selection) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selection_fragment_exoplayer_button.setOnClickListener {
            findNavController().navigate(SelectionFragmentDirections.actionSelectionFragmentToExoplayerFragment())
        }
        selection_fragment_default_button.setOnClickListener {
            findNavController().navigate(SelectionFragmentDirections.actionSelectionFragmentToDefaultPlayerFragment())
        }
    }
}