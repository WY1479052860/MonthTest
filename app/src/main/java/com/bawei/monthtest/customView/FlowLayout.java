package com.bawei.monthtest.customView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.monthtest.R;

/**
 * 自定义view 流式布局
 */
public class FlowLayout  extends ViewGroup {
    private int color;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        color = array.getColor(R.styleable.FlowLayout_text_color, Color.GREEN);
    }
    //测量高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//          super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        compare(width-getPaddingRight());
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       for(int i=0;i<getChildCount();i++){
           View child = getChildAt(i);
           Rect rect= (Rect) child.getTag();
           child.layout(rect.left,rect.top,rect.right,rect.bottom);

           if(child instanceof TextView){
               ((TextView) child).setTextColor(color);
           }
       }
    }
    private void compare(int width) {
        int usedwidth = getPaddingLeft();
        int usedheight = getPaddingTop();
        for(int i=0;i<getChildCount();i++){

            View child = getChildAt(i);

          LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) child.getLayoutParams();
            int childRealWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childRealHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

//            Log.i("xxx",""+usedwidth);
//            Log.i("xxx",""+usedheight);
//            Log.i("xxx",""+width);

            if(usedwidth+childRealWidth>width){

              usedwidth= getPaddingLeft();
              usedheight+=childRealHeight;
            }
            usedwidth+=childRealWidth;

            Rect rect = new Rect();
            rect.left=usedwidth-childRealWidth;
            rect.right=usedwidth;
            rect.top=usedheight;
            rect.bottom=usedheight+childRealHeight;

            child.setTag(rect);

        }


    }

}
