package com.amosh.gctestase.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.amosh.gctestase.bases.BaseActivity
import com.amosh.gctestase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) = Unit
}