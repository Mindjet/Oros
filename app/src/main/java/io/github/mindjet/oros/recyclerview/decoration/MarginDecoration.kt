package io.github.mindjet.oros.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val layoutManager = parent?.layoutManager
        val position = parent?.getChildAdapterPosition(view)
        val size = parent?.adapter?.itemCount
        if (layoutManager is GridLayoutManager) {
            val spanCount = layoutManager.spanCount
            outRect?.apply {
                top = margin
                left = margin
                right = margin
                bottom = margin
            }
//            top row
            if (position!! < spanCount) {
                outRect?.top = margin * 2
            }
            //bottom row
            val distanceToEnd = size?.minus(position)!!
            if (distanceToEnd in 1..(spanCount - 1)) {
                outRect?.bottom = margin * 2
            }
//            //left column
//            if (position.rem(spanCount) == 0) {
//                outRect?.left = margin * 2
//            }
//            //right column
//            if (position.plus(1).rem(spanCount) == 0) {
//                outRect?.right = margin * 2
//            }
        } else if (layoutManager is LinearLayoutManager) {
            outRect?.apply {
                top = margin * 2
                left = margin
                right = margin
                bottom = if (position == size?.minus(1)) margin else 0
            }
        }
    }

}