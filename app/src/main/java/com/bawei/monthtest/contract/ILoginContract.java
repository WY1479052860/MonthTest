package com.bawei.monthtest.contract;

import com.bawei.monthtest.base.IBaseView;

import java.util.HashMap;

/**
 * 登录的契约类
 */
public interface ILoginContract {
    interface ILoginView extends IBaseView{
        void onSuccess(String str);
        void onFailure(String str);
    }
    interface ILoginPresenter{
        void doLogin(String url, HashMap<String,String> map);
    }
    interface ILoginModel{
        void doLogin(String url, HashMap<String,String> map,ILoginCallBack callBack);
        interface ILoginCallBack{
            void onSuccess(String str);
            void onFailure(String str);
        }

    }


}
