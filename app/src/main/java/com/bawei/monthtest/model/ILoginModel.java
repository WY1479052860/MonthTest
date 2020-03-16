package com.bawei.monthtest.model;

import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.bawei.monthtest.contract.ILoginContract;
import com.bawei.monthtest.utils.VolleyUtils;

import java.util.HashMap;

/**
 * 登录的m层
 */
public class ILoginModel  implements ILoginContract.ILoginModel {
    @Override
    public void doLogin(String url, HashMap<String, String> map, final ILoginCallBack callBack) {
        VolleyUtils.getInstance().doPost(url, map, new VolleyUtils.CallBack() {
            @Override
            public void Success(String json) {
                if(callBack!=null){
                    Log.i("xxx",json);
                    callBack.onSuccess(json);
                }

            }

            @Override
            public void Error(String msg) {
                if(callBack!=null){
                    callBack.onFailure(msg);
                }

            }
        });
    }
}
