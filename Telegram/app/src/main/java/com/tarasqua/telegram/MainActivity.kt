package com.tarasqua.telegram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.canhub.cropper.CropImage
import com.tarasqua.telegram.activities.RegisterActivity
import com.tarasqua.telegram.databinding.ActivityMainBinding
import com.tarasqua.telegram.models.User
import com.tarasqua.telegram.ui.fragments.ChatFragment
import com.tarasqua.telegram.ui.objects.AppDrawer
import com.tarasqua.telegram.utilits.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            initFields()
            initFunc()
        }
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }

    override fun onStart() {
        super.onStart()
        AppStates.updataState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updataState(AppStates.OFFLINE)
    }
}