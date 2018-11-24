package io.github.mindjet.oros.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalLinearDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent?.getChildAdapterPosition(view)
        val size = parent?.adapter?.itemCount
        outRect?.apply {
            top = margin * 2
            left = margin
            right = margin
//            bottom = if (position == size?.minus(1)) margin else 0
            bottom = margin
        }

    }

}