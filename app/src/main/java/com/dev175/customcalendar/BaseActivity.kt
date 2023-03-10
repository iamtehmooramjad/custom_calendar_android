package com.dev175.customcalendar

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {
    protected lateinit var bindings: VB
    protected lateinit var context: Context
    protected lateinit var alertDialog: AlertDialog


    open fun initUi() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        bindings = DataBindingUtil.setContentView(this, layoutResId)
        bindings.lifecycleOwner = this
        initUi()
    }


}