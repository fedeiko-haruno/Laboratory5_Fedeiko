package com.example.laboratory5_Fedeiko.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.laboratory5_Fedeiko.Const.Companion.ARG_AVATAR
import com.example.laboratory5_Fedeiko.Const.Companion.ARG_DATA_USER_ID
import com.example.laboratory5_Fedeiko.Const.Companion.ARG_EMAIL
import com.example.laboratory5_Fedeiko.Const.Companion.ARG_FIRSTNAME
import com.example.laboratory5_Fedeiko.Const.Companion.ARG_LASTNAME
import com.example.laboratory5_Fedeiko.R
import com.example.laboratory5_Fedeiko.model.DataUser
import com.example.laboratory5_Fedeiko.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit.*

@AndroidEntryPoint
class EditFragment : Fragment() {

    private lateinit var viewModel: EditViewModel
    private var dataUserId = -1
    private lateinit var avatar: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_edit,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataUserId = arguments?.getInt(ARG_DATA_USER_ID) ?: -1
        avatar = arguments?.getString(ARG_AVATAR).toString()
        Log.d("log", "edit user # $dataUserId")

        setContent()
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        btn_delete_user.setOnClickListener {
            Log.d("log", "delete user")
            viewModel.deleteRecord(viewModel.getDataUserById(dataUserId!!))
            requireActivity().finish()
        }
        btn_save_user.setOnClickListener {
            Log.d("log", "update user")
            updateUser()
            requireActivity().finish()
        }
    }

    private fun setContent() {
        first_name_edit_text.setText(arguments?.getString(ARG_FIRSTNAME))
        last_name_edit_text.setText(arguments?.getString(ARG_LASTNAME))
        email_edit_text.setText(arguments?.getString(ARG_EMAIL))
    }

    private fun updateUser() {
        val firstname = first_name_edit_text.text.toString()
        val lastname = last_name_edit_text.text.toString()
        val email = email_edit_text.text.toString()
        val user = DataUser(
            first_name = firstname,
            last_name = lastname,
            email = email,
            id = dataUserId,
            avatar = avatar
        )
        viewModel.updateRecord(user)
    }
}