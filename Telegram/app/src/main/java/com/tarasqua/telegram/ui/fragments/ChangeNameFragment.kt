package com.tarasqua.telegram.ui.fragments

import com.tarasqua.telegram.R
import com.tarasqua.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {
    override fun onResume() {
        super.onResume()
        initFullNameList()
    }

    private fun initFullNameList() {
        val fullNameList = USER.fullName.split(" ")
        if (fullNameList.size > 1) {
            settings_input_name.setText(fullNameList[0])
            settings_input_surname.setText(fullNameList[1])
        } else {
            settings_input_name.setText(fullNameList[0])
        }
    }

    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME).setValue(fullname)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        showToast(getString(R.string.toast_data_update))
                        USER.fullName = fullname
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}