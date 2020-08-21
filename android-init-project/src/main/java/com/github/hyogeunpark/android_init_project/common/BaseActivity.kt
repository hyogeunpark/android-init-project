package com.github.hyogeunpark.android_init_project.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), InitSettingImpl {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLayoutView()?.run { setContentView(this) } ?: setContentView(activityLayoutId())
        intent?.let(::loadIntentBeforeInit)
        init()
        onBind()
        loadData()
    }

    protected abstract fun activityLayoutId(): Int

    protected open fun activityLayoutView(): View? = null
}