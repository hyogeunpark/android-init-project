package com.github.hyogeunpark.android_init_project.common

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.github.hyogeunpark.android_init_project.R

abstract class ViewBindingActivity<VB : ViewBinding>: BaseActivity() {
    protected val binding: VB by lazy { initViewBinding() }
    private val toolbar: Toolbar? by lazy { getSupportToolbar() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent?.let(::loadIntentBeforeInit)
        init()
        onBind()
        loadData()
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        toolbar?.let(::setSupportActionBar)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        toolbar?.let(::setSupportActionBar)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        toolbar?.let(::setSupportActionBar)
    }

    protected abstract fun initViewBinding(): VB
    protected open fun getSupportToolbar(): Toolbar? = null

    override fun activityLayoutId(): Int = R.layout.activity_base
}