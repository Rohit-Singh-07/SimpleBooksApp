package com.example.bookshub.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshub.R

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<String>) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    // ViewHolder class to hold the reference to the views in the item
    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.book_title)
    }

    // Inflate the item layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        // Inflate the layout for each item in the RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.relycler_dashboard_single_row, parent, false)
        return DashboardViewHolder(view)
    }

    // Bind the data to the item view
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = itemList[position]  // Get the data for the current position
        holder.textView.text = item  // Set the data to the TextView (or any other view)
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }
}
