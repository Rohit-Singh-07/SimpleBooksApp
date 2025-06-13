package com.example.bookshub.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshub.R

class DashboardRecyclerAdapter(
    val context: Context,
    val itemList: ArrayList<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    // ViewHolder class
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.book_title)
        val cardView: CardView = view.findViewById(R.id.bookCard)  // Assuming cardView has this ID
    }

    // Define an interface for handling item clicks
    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

    // Inflate the item layout and create a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.relycler_dashboard_single_row, parent, false)
        return DashboardViewHolder(view)
    }

    // Bind the data to the item view
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = itemList[position]  // Get the data for the current position
        holder.textView.text = item  // Set the data to the TextView

        // Set the click listener for the item
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)  // Notify the listener about the click event
        }
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }
}
