package com.percenttextview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class PercentTextView: TextView {

    private var mMaxPercentWidth = 1f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val array = context?.obtainStyledAttributes(attrs, R.styleable.PercentTextView, defStyleAttr, 0)
        array?.let {
            mMaxPercentWidth = array.getFloat(R.styleable.PercentTextView_max_percent_width, 1f)
            it.recycle()
        }

        if (mMaxPercentWidth > 1f || mMaxPercentWidth < 0f) {
            throw IllegalArgumentException("max_percent_width should be between 0 to 1")
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        var newWidth = measuredWidth
        if (measuredWidth > (parentWidth*mMaxPercentWidth)) {
            newWidth = (parentWidth*mMaxPercentWidth).toInt()
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY), heightMeasureSpec)
    }
}