package com.example.bookshub.ui  // <-- This is the key line. Check if it matches the graph

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshub.R
import com.example.bookshub.ui.DashboardRecyclerAdapter
import com.example.bookshub.utils.ConnectionManager

class HomeFragment : Fragment(), DashboardRecyclerAdapter.OnItemClickListener {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val bookList = arrayListOf(
        "To Kill a Mockingbird",
        "1984",
        "The Great Gatsby",
        "The Catcher in the Rye",
        "Moby-Dick",
        "Pride and Prejudice",
        "The Hobbit",
        "Brave New World",
        "Harry Potter and the Sorcerer's Stone",
        "The Lord of the Rings"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        if (ConnectionManager().checkConnectivity(activity as Context)) {
            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
        } else {
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Failed to Connect")
            dialog.setMessage("Internet Connection not found")
            dialog.setPositiveButton("OK") { _, _ ->
            }
            dialog.create()
            dialog.show()
        }


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView and LayoutManager
        recyclerDashboard = view.findViewById(R.id.recycle_view)
        layoutManager = LinearLayoutManager(activity)

        // Initialize Adapter with the listener
        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookList, this)

        // Set the LayoutManager and Adapter
        recyclerDashboard.layoutManager = layoutManager
        recyclerDashboard.adapter = recyclerAdapter

        return view
    }

    // Implement the interface method to handle item clicks
    override fun onItemClick(item: String) {
        // Handle the click event (e.g., show a Toast, navigate, etc.)
        Toast.makeText(activity, "Clicked on: $item", Toast.LENGTH_SHORT).show()
    }
}
