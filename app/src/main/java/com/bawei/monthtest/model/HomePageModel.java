package com.bawei.monthtest.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.Volley;
import com.bawei.monthtest.contract.IHomePageContract;
import com.bawei.monthtest.contract.ILoginContract;
import com.bawei.monthtest.utils.VolleyUtils;

import java.util.HashMap;

/**
 * 首页的m层
 */
public class HomePageModel implements IHomePageContract.IModel {

    @Override
    public void getBanner(String url, final IGetBannerCall call) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.CallBack() {
            @Override
            public void Success(String json) {
                call.getBannerSuccess(json);
            }

            @Override
            public void Error(String msg) {
                call.getBannerFailure(msg);
            }
        });
    }

    @Override
    public void getList(String url, final IGetListBack back) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.CallBack() {
            @Override
            public void Success(String json) {
                back.getListSuccess(json);
            }

            @Override
            public void Error(String msg) {
                back.getListError(msg);
            }
        });
    }
}
