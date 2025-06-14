package com.example.bookshub.ui  // <-- This is the key line. Check if it matches the graph

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import com.android.volley.Request
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookshub.R
import com.example.bookshub.models.Book
import com.example.bookshub.ui.DashboardRecyclerAdapter
import com.example.bookshub.utils.ConnectionManager

class HomeFragment : Fragment(), DashboardRecyclerAdapter.OnItemClickListener {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val bookList = mutableListOf<Book>() // empty mutable list of books

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerDashboard = view.findViewById(R.id.recycle_view)
        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(requireContext(), bookList, this)
        recyclerDashboard.layoutManager = layoutManager
        recyclerDashboard.adapter = recyclerAdapter

        if (ConnectionManager().checkConnectivity(requireContext())) {
            fetchBooks()
        } else {
            AlertDialog.Builder(requireContext())
                .setTitle("Failed to Connect")
                .setMessage("Internet Connection not found")
                .setPositiveButton("OK", null)
                .show()
        }

        return view
    }

    private fun fetchBooks() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://www.dbooks.org/api/recent"

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val booksArray = response.getJSONArray("books")
                bookList.clear()  // clear old list

                for (i in 0 until booksArray.length()) {
                    val bookJson = booksArray.getJSONObject(i)
                    val book = Book(
                        id = bookJson.getString("id"),
                        title = bookJson.getString("title"),
                        authors = bookJson.getString("authors"),
                        image = bookJson.getString("image"),
                        url = bookJson.getString("url")
                    )
                    bookList.add(book)
                }
                recyclerAdapter.notifyDataSetChanged()  // refresh list on UI
            },
            Response.ErrorListener { error ->
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return hashMapOf("Content-Type" to "application/json")
            }
        }

        queue.add(jsonObjectRequest)
    }

    override fun onItemClick(item: Book) {
        val intent = Intent(requireContext(), BookDetailActivity::class.java).apply {
            putExtra("id", item.id)
            putExtra("title", item.title)
            putExtra("authors", item.authors)
            putExtra("image", item.image)
            putExtra("url", item.url)
        }
        startActivity(intent)
    }

}
