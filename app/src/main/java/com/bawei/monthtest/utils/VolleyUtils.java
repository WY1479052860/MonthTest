package com.bawei.monthtest.utils;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.monthtest.base.App;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络工具类
 */
public class VolleyUtils {
    //请求队列
     RequestQueue queue;
   //静态内部类 单例模式
    private VolleyUtils(){
        queue = Volley.newRequestQueue(App.getAppContext());

    }
    private static class SingleInstance{
        private static final VolleyUtils INSTANCE=new VolleyUtils();
    }

    public static VolleyUtils getInstance() {
        return SingleInstance.INSTANCE;
    }

    //接口回调
    public interface CallBack{
        void Success(String json);
        void Error(String  msg);
    }
    //get请求
    public void  doGet(String url, final @NonNull CallBack callBack){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    callBack.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    callBack.Error(error.getMessage());
            }
        });
        queue.add(request);
    }
    //post请求
    public void doPost(String url, final HashMap<String,String> map, final CallBack callBack){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (callBack != null) {
                    callBack.Success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callBack != null) {
                    callBack.Success(error.getMessage());
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        queue.add(request);
    }

}
