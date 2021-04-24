package com.medha.parkplusdemo.ui.activities


import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Medha Singh
 * Base activity to handle common use cases on certain lifecycle methods
 */
open class BaseActivity : AppCompatActivity() {


    override fun onPause() {
        super.onPause()
        // hide keyboard onPause
        hideKeyboard()

    }

    fun hideKeyboard() { // Check if no view has focus:
        try {
            val view = this.currentFocus
            if (view != null) {
                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * finishes previous activity
     */
    fun startActivityAndFinish(intent: Intent) {
        startActivity(intent)
        finish()
    }

    /**
     * clears all previous activity before starting the given intent
     */
    fun startActivityWithClearFlag(intent: Intent) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    /**
     * Safe click listener to avoid multiple clicks successively
     * It waits for one second to register another click
     */
    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }

    internal class SafeClickListener(
        private var defaultInterval: Int = 1000,
        private val onSafeCLick: (View) -> Unit
    ) : View.OnClickListener {
        private var lastTimeClicked: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
                return
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeCLick(v)
        }
    }


}