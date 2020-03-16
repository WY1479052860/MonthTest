package com.bawei.monthtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.monthtest.R;
import com.bawei.monthtest.customView.CustomSearchView;
import com.bawei.monthtest.customView.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends AppCompatActivity {

    private CustomSearchView csv_aty;
    List<String> list = new ArrayList<>();
    private FlowLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }
    private void initView() {
        csv_aty = findViewById(R.id.csv_aty);
        fl = findViewById(R.id.fl);
        csv_aty.setOnSearchClick(new CustomSearchView.OnSearchClick() {
            @Override
            public void onSerachCilck(String str) {
                list.add(str);
                Log.i("xxx",list.size()+"");
                fl.removeAllViews();
                for(String string:list){
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10,10,10,10);

                    TextView view = new TextView(SearchActivity.this);
                    view.setPadding(5,5,5,5);
                    view.setText(string);
                    view.setLayoutParams(layoutParams);
                    fl.addView(view,layoutParams);
                }

            }
        });


    }
    private void initData() {
        csv_aty.setData(getIntent().getStringExtra("searchKey"));

    }

}
