package com.tarasqua.telegram.ui.fragments

import com.tarasqua.telegram.R
import com.tarasqua.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_user_name.*
import java.util.*

class ChangeUserNameFragment : BaseChangeFragment(R.layout.fragment_change_user_name) {

    lateinit var mNewUserName: String

    override fun onResume() {
        super.onResume()
        settings_input_userName.setText(USER.userName)
    }

    override fun change() {
        mNewUserName = settings_input_userName.text.toString().lowercase(Locale.getDefault())
        if (mNewUserName.isEmpty()) {
            showToast("Введите имя")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES).addListenerForSingleValueEvent(AppValueEventListener{
                if (it.hasChild(mNewUserName)){
                    showToast("Пользователь с таким именем уже существует")
                }else{
                    changeUserName()
                }
            })
        }
    }

    private fun changeUserName() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUserName()
                }
            }
    }

    private fun updateCurrentUserName() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME)
            .setValue(mNewUserName)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    deleteOldUserName()
                } else{
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUserName() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.userName).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    fragmentManager?.popBackStack()
                    USER.userName = mNewUserName
                } else{
                    showToast(it.exception?.message.toString())
                }
            }
    }
}