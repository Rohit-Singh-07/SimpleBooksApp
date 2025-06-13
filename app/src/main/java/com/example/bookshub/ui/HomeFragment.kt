package com.example.bookshub.ui  // <-- This is the key line. Check if it matches the graph

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshub.R
import com.example.bookshub.ui.DashboardRecyclerAdapter

class HomeFragment : Fragment() {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManage: RecyclerView.LayoutManager

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

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerDashboard = view.findViewById(R.id.recycle_view)
        layoutManage = LinearLayoutManager(activity)
        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookList)

        recyclerDashboard.layoutManager = layoutManage
        recyclerDashboard.adapter = recyclerAdapter

        return view
    }
}
