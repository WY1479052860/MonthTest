package com.bawei.monthtest.presenter;

import android.util.Log;

import com.bawei.monthtest.base.BasePresenter;
import com.bawei.monthtest.base.IBaseView;
import com.bawei.monthtest.contract.ILoginContract;
import com.bawei.monthtest.model.ILoginModel;

import java.util.HashMap;

/**
 * 登录p层
 */
public class ILoginPresenter extends BasePresenter implements ILoginContract.ILoginPresenter {
     ILoginModel model;

    public ILoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }


    @Override
    public void doLogin(String url, HashMap<String, String> map) {
        model.doLogin(url, map, new ILoginContract.ILoginModel.ILoginCallBack() {
            @Override
            public void onSuccess(String str) {
                Log.i("xxx",str);
                IBaseView iBaseView = getView();

                if (iBaseView instanceof ILoginContract.ILoginView) {
                    ILoginContract.ILoginView view = (ILoginContract.ILoginView) iBaseView;
                    view.onSuccess(str);
                    Log.i("xxx","Aaa");
                }
            }

            @Override
            public void onFailure(String str) {
//                Log.i("xxx",str);
                IBaseView iBaseView = getView();
                if(iBaseView instanceof  ILoginContract.ILoginView){
                    ILoginContract.ILoginView view= (ILoginContract.ILoginView) iBaseView;
                    view.onFailure(str);
                }
            }
        });


    }
    @Override
    protected void initModel() {
        model = new ILoginModel();
    }
}
