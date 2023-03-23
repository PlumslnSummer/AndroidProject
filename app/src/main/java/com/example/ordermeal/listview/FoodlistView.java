package com.example.ordermeal.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class FoodlistView extends ListView {
    public FoodlistView(Context context) {
        super(context);
    }

    public FoodlistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FoodlistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expected=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expected);
    }
}
