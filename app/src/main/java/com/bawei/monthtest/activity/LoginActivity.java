package com.bawei.monthtest.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.monthtest.R;
import com.bawei.monthtest.base.BaseActivity;
import com.bawei.monthtest.base.BasePresenter;
import com.bawei.monthtest.bean.LoginBean;
import com.bawei.monthtest.contract.ILoginContract;
import com.bawei.monthtest.presenter.ILoginPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    private EditText et1;
    private EditText et2;
    private Button bt;

    @Override
    protected BasePresenter initPresenter() {
        return new ILoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //找控件
        et1 = findViewById(R.id.et_name);
        et2 = findViewById(R.id.et_pwd);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePresenter presenter = getPresenter();
                if(presenter instanceof ILoginPresenter){
                    String phone = et1.getText().toString();
                    String pwd = et2.getText().toString();
                    if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                        return;
                    }
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phone",phone);
                    map.put("pwd",pwd);
                    ILoginPresenter loginPresenter= (ILoginPresenter) presenter;
                    loginPresenter.doLogin("http://mobile.bwstudent.com/small/user/v1/login",map);
                }
            }
        });

    }


    @Override
    public void onSuccess(String str) {

        Gson gson = new Gson();
        LoginBean bean = gson.fromJson(str, LoginBean.class);
        String message = bean.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("userId", bean.getResult().getUserId());
        edit.putString("sessionId",bean.getResult().getSessionId());
        edit.commit();


        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void initData() {

    }
}
