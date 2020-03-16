package com.bawei.monthtest.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bawei.monthtest.R;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{
    P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        presenter= initPresenter();
        initData();
    }

    public P getPresenter() {
        return presenter;
    }


    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract P initPresenter();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
            presenter=null;
        }
    }
}
