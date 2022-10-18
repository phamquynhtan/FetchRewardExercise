package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

 class ListAdapter (

    private var list : ArrayList<ListViewModel>,) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
        override fun onCreateViewHolder(

            parent : ViewGroup,
            viewType: Int
        ) : ListViewHolder{

            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.lvm_layout,
                parent, false
            )

            return ListViewHolder(itemView)
        }

     override fun onBindViewHolder(holder: ListViewHolder, positionId: Int) {
         holder.id.text = list.get(positionId).id.toString()
         holder.listId.text = list.get(positionId).listId.toString()
         holder.name.text = list.get(positionId).name
     }

     override fun getItemCount(): Int {
        return list.size
     }
    class ListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val id : TextView = itemView.findViewById(R.id.idtxt)
        val listId : TextView = itemView.findViewById(R.id.listidtxt)
        val name : TextView = itemView.findViewById(R.id.nametxt)
    }
    }
