package com.bawei.monthtest.contract;

import com.bawei.monthtest.base.IBaseView;

/**
 * 契约类
 */
public interface IHomePageContract {
    interface IView extends IBaseView {

        void getBannerSuccess(String str);
        void getBannerFailure(String str);

        void getListSuccess(String str);
        void getListError(String str);
    }
    interface IPresenter{
        void getBanner(String url);
        void getList(String url );
    }
    interface IModel{
        void getBanner(String url,IGetBannerCall call);
        interface IGetBannerCall{
            void getBannerSuccess(String str);
            void getBannerFailure(String str);
        }

        void getList(String url,IGetListBack back);
        interface IGetListBack{
            void getListSuccess(String str);
            void getListError(String str);
        }
    }


}
