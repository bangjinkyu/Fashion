package com.room.fashion.util

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingRecyclerViewCallback(
    private val onScrolledToBottom: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    private var currentPage = 0

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)
        val layoutManager = view.layoutManager as GridLayoutManager

        val totalCount = layoutManager.getItemCount() -1
        val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()

        Log.d("room", "lastVisibleItem $lastVisibleItem, totalCount $totalCount")
        if (lastVisibleItem == totalCount) {
            currentPage++
            Log.d("room", "currentPage $currentPage")
            onScrolledToBottom(currentPage)
        }
    }
}