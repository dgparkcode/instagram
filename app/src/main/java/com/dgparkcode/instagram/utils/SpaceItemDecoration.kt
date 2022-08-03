package com.dgparkcode.instagram.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val spaceSize: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        when (val layoutManager = parent.layoutManager) {
            is LinearLayoutManager -> {
                val isVertical = RecyclerView.VERTICAL == layoutManager.orientation
                if (isVertical && position > 0) outRect.top = spaceSize
                if (!isVertical && position > 0) outRect.left = spaceSize
            }
            else -> Unit
        }
    }
}