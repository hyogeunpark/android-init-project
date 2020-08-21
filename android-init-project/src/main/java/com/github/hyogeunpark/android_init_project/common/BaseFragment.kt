package com.github.hyogeunpark.android_init_project.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), InitFragmentSettingImpl {
    protected var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) rootView = inflater.inflate(fragmentLayoutId(), container, false)
        return rootView
    }

    protected abstract fun fragmentLayoutId(): Int
}