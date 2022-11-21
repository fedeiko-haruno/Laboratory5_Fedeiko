package com.example.laboratory5_Fedeiko.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laboratory5_Fedeiko.R
import com.example.laboratory5_Fedeiko.model.DataUser
import kotlinx.android.synthetic.main.user_item_card.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var listDataUser: List<DataUser>? = null

    fun setListData(listDataUser: List<DataUser>?) {
        this.listDataUser = listDataUser
    }

    var onDataUserClickListener: ((DataUser) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_item_card, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val dataUser = listDataUser?.get(position)
        holder.itemView.setOnClickListener {
            if (dataUser != null) {
                onDataUserClickListener?.invoke(dataUser)
            }
            true
        }
        holder.bind(listDataUser?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listDataUser == null) return 0
        return listDataUser?.size!!
    }

    class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val userIdTextView = view.userIdTextView
        private val userAvatarImageView = view.userAvatarImageView
        private val userFirstNameTextView = view.userFirstNameTextView
        private val userLastNameTextView = view.userLastNameTextView
        private val userEmailTextView = view.userEmailTextView


        fun bind(dataUser: DataUser) {
            userIdTextView.text = dataUser.id.toString()
            userFirstNameTextView.text = dataUser.first_name
            userLastNameTextView.text = dataUser.last_name
            userEmailTextView.text = dataUser.email

            Glide.with(userAvatarImageView)
                .load(dataUser.avatar)
                .into(userAvatarImageView)
        }
    }

    interface OnDataUserClickListener {
        fun onDataUserClick(dataUser: DataUser)
    }
}