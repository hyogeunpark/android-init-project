package com.github.hyogeunpark.android_init_project.extention

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.github.hyogeunpark.android_init_project.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * @param target : 이동할 화면의 class
 */
fun Context.moveToPage(target: Class<out Activity>) {
    this.startActivity(Intent(this, target))
}

/**
 * @param target : 이동할 화면의 intent
 */
fun Context.moveToPage(target: Intent) {
    this.startActivity(target)
}

/**
 * @param dp : dp 값
 * @return dp를 px로 변환한 값
 */
fun Context.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
}

/**
 * @param colorId : resource id
 * @return color
 */
fun Context.getResourceColor(colorId: Int) = ContextCompat.getColor(this, colorId)

/**
 * @param message : 토스트에 표시할 메시지
 */
fun Context.showToast(message: String?) {
    if (message.isNullOrEmpty()) return
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * @param view : 스낵바를 띄울 view
 * @param message : 스낵바에 표시할 메시지
 */
fun Context.showSnackbar(view: View?, message: String?) {
    if (view == null || message.isNullOrEmpty()) return
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * @param message : 스낵바에 표시할 메시지
 */
fun ViewBinding.showSnackbar(message: String?) {
    if (message.isNullOrEmpty()) return
    Snackbar.make(this.root, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * @param title : dialog 제목
 * @param contents : dialog 내용
 * @param positiveText : positive 버튼 텍스트
 * @param positiveOnClickListener : positive 버튼 클릭 리스너
 * @param negativeText : negative 버튼 텍스트
 * @param negativeOnClickListener : negative 버튼 클릭 리스너
 */
fun Context.showDialog(title: String? = "", contents: String? = "", positiveText: String? = "", positiveOnClickListener: DialogInterface.OnClickListener? = null, negativeText: String? = "", negativeOnClickListener: DialogInterface.OnClickListener? = null) {
    val dialogBuilder = MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
    // 타이틀
    if (!title.isNullOrEmpty()) dialogBuilder.setTitle(title)
    // 내용
    if (!contents.isNullOrEmpty()) dialogBuilder.setMessage(contents)
    // 확인 버튼
    if (!positiveText.isNullOrEmpty()) dialogBuilder.setPositiveButton(positiveText, positiveOnClickListener)
    // 취소 버튼
    if (!negativeText.isNullOrEmpty()) dialogBuilder.setNegativeButton(negativeText, negativeOnClickListener)

    dialogBuilder.show()
}