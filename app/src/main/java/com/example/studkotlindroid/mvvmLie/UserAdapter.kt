package com.example.studkotlindroid.mvvmLie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import studkotlindroid.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private var users: List<User> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: UserHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount() = users.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* something very very strange */
        private val userName : TextView = itemView.findViewById(R.id.userName)
        private val userDescription : TextView = itemView.findViewById(R.id.userDescription)

        fun bind(user: User) = with(itemView) {
            userName.text = user.name
            userDescription.text = user.description
        }
    }
}