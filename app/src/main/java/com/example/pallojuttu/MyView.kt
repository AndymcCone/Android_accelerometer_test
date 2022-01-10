package com.example.pallojuttu

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint()
    var x1 = 450f
    var y1 = 900f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.DKGRAY

        canvas?.drawOval(x1, y1, x1 +150, y1 + 150, paint)
    }

    fun setXY(x: Float, y: Float) {

        if (x1 > 0)
        {
            x1 -= x
        }
        else{
            x1 = 1f
        }
        if (x1 < width - 150)
        {
            x1 -= x
        }
        else{
            x1 = width - 151f
        }
        if (y1 > 0)
        {
            y1 += y
        }
        else{
            y1 = 1f
        }
        if (y1 < height - 150)
        {
            y1 += y
        }
        else{
            y1 = height - 151f
        }

        invalidate()
    }


}