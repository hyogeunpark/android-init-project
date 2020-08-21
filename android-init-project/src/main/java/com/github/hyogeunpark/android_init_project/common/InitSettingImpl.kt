package com.github.hyogeunpark.android_init_project.common

import android.content.Intent

interface InitSettingImpl {
    fun loadIntentBeforeInit(intent: Intent)
    fun init()
    fun onBind()
    fun loadData()
}