package com.bawei.monthtest.presenter;

import com.bawei.monthtest.base.BasePresenter;
import com.bawei.monthtest.base.IBaseView;
import com.bawei.monthtest.contract.IHomePageContract;
import com.bawei.monthtest.model.HomePageModel;

/**
 * 首页的p层
 */
public class HomePagePresenter extends BasePresenter implements IHomePageContract.IPresenter {

    HomePageModel model;

    public HomePagePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new HomePageModel();

    }

    @Override
    public void getBanner(String url) {
        model.getBanner(url, new IHomePageContract.IModel.IGetBannerCall() {
            @Override
            public void getBannerSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView iview= (IHomePageContract.IView) view;
                    iview.getBannerSuccess(str);
                }

            }

            @Override
            public void getBannerFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView Iview= (IHomePageContract.IView) view;
                    Iview.getBannerFailure(str);
                }
            }
        });

    }

    @Override
    public void getList(String url) {
        model.getList(url, new IHomePageContract.IModel.IGetListBack() {
            @Override
            public void getListSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView Iview= (IHomePageContract.IView) view;
                    Iview.getListSuccess(str);
                }
            }

            @Override
            public void getListError(String str) {
                IBaseView view = getView();
                if(view instanceof IHomePageContract.IView){
                    IHomePageContract.IView Iview= (IHomePageContract.IView) view;
                    Iview.getListError(str);
                }
            }
        });

    }
}
