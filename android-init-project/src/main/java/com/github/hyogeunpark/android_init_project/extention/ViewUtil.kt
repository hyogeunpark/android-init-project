package com.github.hyogeunpark.android_init_project.extention

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .thumbnail(0.1f)
        .format(DecodeFormat.PREFER_ARGB_8888)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)
}

fun Context.windowSize(): DisplayMetrics {
    val outMetrics = DisplayMetrics()
    (this.applicationContext.getSystemService(Context.WINDOW_SERVICE) as? WindowManager)?.defaultDisplay?.getMetrics(outMetrics)
    return outMetrics
}

/**
 * 디바이스 화면의 가로 사이즈 구함
 *
 * @return : 화면의 가로 사이즈
 */
fun Context.windowWidth(): Int {
    return windowSize().widthPixels
}

/**
 * 디바이스 화면의 세로 사이즈 구함
 *
 * @return : 화면의 세로 사이즈
 */
fun Context.windowHeight(): Int {
    return windowSize().heightPixels
}
/**
 * 디바이스 status bar 사이즈 구함
 *
 * @return : status bar 사이즈
 */
fun Context.statusBarHeight(): Int {
    val resId = this.applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resId > 0) this.applicationContext.resources.getDimensionPixelSize(resId) else 0
}

/**
 * 키보드 노출
 */
fun EditText.showKeyboard() {
    this.requestFocus()
    val imm = this.context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

/**
 * 키보드 숨기기
 */
fun EditText.hideKeyboard() {
    this.clearFocus()
    val imm = this.context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * 키보드 노출 유무(rootView로 호출해야 함)
 * @return 키보드 노출 유무
 */
fun View.isKeyboardShown(): Boolean {
    return Rect().apply(this::getWindowVisibleDisplayFrame).let { rect ->
        val softKeyboardHeight = 100
        val dm = this.resources.displayMetrics
        val heightDiff = this.bottom - rect.bottom
        heightDiff > softKeyboardHeight * dm.density
    }
}