package com.example.unitconverter.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.unitconverter.R
import java.lang.reflect.Field

object Utility {

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    fun getStringResourceId(context: Context, stringToSearch: String): Int {
        val fields: Array<Field> = R.string::class.java.fields
        for (field in fields) {
            val id = field.getInt(field)
            val str = context.resources.getString(id)
            if (str == stringToSearch) {
                return id
            }
        }
        return -1
    }
}