package com.dgparkcode.instagram.feature.main

import android.os.Bundle
import android.view.View
import com.dgparkcode.instagram.base.BaseFragment
import com.dgparkcode.instagram.databinding.FragmentVideosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment : BaseFragment<FragmentVideosBinding>(FragmentVideosBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}