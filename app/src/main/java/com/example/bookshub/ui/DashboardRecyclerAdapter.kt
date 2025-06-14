package com.example.bookshub.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshub.R
import com.example.bookshub.models.Book  // Ensure Book model is imported

class DashboardRecyclerAdapter(
    private val context: Context,
    private val itemList: MutableList<Book>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.book_title)
        val cardView: CardView = view.findViewById(R.id.bookCard)
    }

    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.relycler_dashboard_single_row, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.textView.text = book.title

        holder.itemView.setOnClickListener {
            listener.onItemClick(book)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
