package com.example.firebaselesson.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.models.User

class UserRecyclerAdapter: RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(), Filterable {


    var items: MutableList<User> = mutableListOf()
        set(value){
            field = value
            usersFilteredList = value
            notifyDataSetChanged()
        }

    private var usersFilteredList: MutableList<User> = mutableListOf()

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().trim()
                if(charSearch.isEmpty()){
                    usersFilteredList = items
                } else {
                    val resultList = items.filter {it.fullName.lowercase().contains(charSearch.lowercase())}
                    usersFilteredList = resultList as MutableList<User>
                }
                val filterResults = FilterResults()
                filterResults.values = usersFilteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                usersFilteredList = results?.values as MutableList<User>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = usersFilteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersFilteredList[position]
        holder.bind(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShortName: TextView = itemView.findViewById(R.id.tvShotName)
        val tvName: TextView = itemView.findViewById(R.id.tvName)

        fun bind(user: User){
            tvShortName.text = user.fullName[0].toString().trim()
            tvName.text = user.fullName
        }
    }


}