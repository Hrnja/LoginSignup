package com.example.loginsignup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.loginsignup.model.LogSigEntity
import com.example.loginsignup.viewmodel.LogSigViewModel


class RVAdapter( private val logSigViewModel: LogSigViewModel, val context: Context) : RecyclerView.Adapter<RVAdapter.ViewHolder>()
{

    private val allUsers = ArrayList<LogSigEntity>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(R.id.recUserName)
        val lastname = itemView.findViewById<TextView>(R.id.recUserLastName)
        val email = itemView.findViewById<TextView>(R.id.recUserEmail)
        val password = itemView.findViewById<TextView>(R.id.recUserPassword)
        val delete = itemView.findViewById<Button>(R.id.delete)

        fun bind(user: LogSigEntity) {
            name.text = user.name
            lastname.text = user.lastname
            email.text = user.email
            password.text = user.password
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(allUsers.get(position).name)
        holder.lastname.setText(allUsers.get(position).lastname)
        holder.email.setText(allUsers.get(position).email)
        holder.password.setText(allUsers.get(position).password)

        val currentUser = allUsers[position]
        holder.bind(currentUser)

        holder.delete.setOnClickListener {
            logSigViewModel.deleteUser(currentUser)
            allUsers.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun updateList(newList:List<LogSigEntity>) {
        allUsers.clear()
        allUsers.addAll(newList)
        notifyDataSetChanged()
    }


}