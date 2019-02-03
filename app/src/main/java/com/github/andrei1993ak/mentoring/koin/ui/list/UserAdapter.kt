package com.github.andrei1993ak.mentoring.koin.ui.list

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.andrei1993ak.mentoring.koin.R
import com.github.andrei1993ak.mentoring.koin.databinding.ItemUserBinding
import com.github.andrei1993ak.mentoring.koin.repository.User

class UserAdapter(
    private val context: Context,
    private val clickListener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_user,
                parent,
                false
            ), clickListener
        )
    }

    override fun getItemId(position: Int): Long {
        return users[position].userId
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun updateUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)

        notifyDataSetChanged()
    }

    fun removeUser(id: Long) {
        val position = getPositionById(id)

        this.users.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun getPositionById(id: Long): Int {
        for (i in users.indices) {
            if (users[i].userId == id) {
                return i
            }
        }

        return -1
    }

    class UserViewHolder(private val binding: ItemUserBinding, private val clickListener: OnUserClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}