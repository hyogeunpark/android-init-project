package com.github.hyogeunpark.android_init_project.extention

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * @return html로 된 문자열을 Spanned로 변환
 */
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}