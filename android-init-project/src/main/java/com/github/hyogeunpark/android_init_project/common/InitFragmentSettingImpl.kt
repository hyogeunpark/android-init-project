package com.github.hyogeunpark.android_init_project.common

import android.os.Bundle
import android.view.View

interface InitFragmentSettingImpl {
    fun loadArgumentBeforeInit(arguments: Bundle)
    fun init(view: View)
    fun onBind()
    fun loadData()
}