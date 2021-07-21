package com.tarasqua.telegram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.tarasqua.telegram.R
import com.tarasqua.telegram.databinding.ActivityRegisterBinding
import com.tarasqua.telegram.ui.fragments.EnterPhoneNumberFragment
import com.tarasqua.telegram.utilits.initFirebase
import com.tarasqua.telegram.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mTooolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mTooolbar = mBinding.registerToolbar
        setSupportActionBar(mTooolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment())
    }
}