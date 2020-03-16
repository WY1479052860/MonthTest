package com.bawei.monthtest.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bawei.monthtest.R;

/**
 * 自定义view  搜索
 */
public class CustomSearchView  extends LinearLayout {

    private EditText et;
    private ImageView iv;

    public CustomSearchView(Context context) {
        super(context);
        init(context);
    }

    public CustomSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        View view = View.inflate(context, R.layout.serch_view, null);
        et = view.findViewById(R.id.et);
        iv = view.findViewById(R.id.img);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSearchClick!=null){
                    mOnSearchClick.onSerachCilck(et.getText().toString());
                }
            }

        });
        addView(view);
    }
    public void setData(String str){
        et.setText(str);
    }

    private OnSearchClick mOnSearchClick;
    public void setOnSearchClick(OnSearchClick onSearchClick){
        mOnSearchClick=onSearchClick;
    }



    public interface OnSearchClick{
        void onSerachCilck(String str);
    }
}
