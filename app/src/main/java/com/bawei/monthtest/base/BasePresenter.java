package com.bawei.monthtest.base;

import java.lang.ref.WeakReference;

/**
 * BasePresenter mvp进阶
 */
public  abstract class BasePresenter <V extends IBaseView> {
    WeakReference<V> weakReference;

    public BasePresenter(V v) {
        weakReference=new WeakReference<>(v);
        initModel();

    }
    public V getView(){
        if(weakReference!=null){
            return weakReference.get();
        }
        return null;
    }
    public void detachView(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }

    protected abstract void initModel();
}
