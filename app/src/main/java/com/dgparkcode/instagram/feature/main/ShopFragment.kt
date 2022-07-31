package com.dgparkcode.instagram.feature.main

import android.os.Bundle
import android.view.View
import com.dgparkcode.instagram.base.BaseFragment
import com.dgparkcode.instagram.databinding.FragmentShopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding>(FragmentShopBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}