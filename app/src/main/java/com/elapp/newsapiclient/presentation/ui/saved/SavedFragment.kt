package com.elapp.newsapiclient.presentation.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elapp.newsapiclient.databinding.FragmentSavedBinding

class SavedFragment: Fragment() {
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

}