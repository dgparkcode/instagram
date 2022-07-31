package com.dgparkcode.instagram.feature.main

import android.os.Bundle
import android.view.View
import com.dgparkcode.instagram.base.BaseFragment
import com.dgparkcode.instagram.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}