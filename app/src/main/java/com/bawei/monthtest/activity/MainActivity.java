package com.bawei.monthtest.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.monthtest.R;
import com.bawei.monthtest.adapter.RecyclerViewAdapter;
import com.bawei.monthtest.adapter.RecyclerViewAdapterThree;
import com.bawei.monthtest.adapter.RecyclerViewAdapterTwo;
import com.bawei.monthtest.base.BaseActivity;
import com.bawei.monthtest.base.BasePresenter;
import com.bawei.monthtest.bean.ListBean;
import com.bawei.monthtest.bean.XbanBean;
import com.bawei.monthtest.contract.IHomePageContract;
import com.bawei.monthtest.customView.CustomSearchView;
import com.bawei.monthtest.presenter.HomePagePresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements IHomePageContract.IView {

    private XBanner xbn;
    private RecyclerView rc_h;
    private RecyclerView rc_g;
    private RecyclerView rc_v;
    private List<XbanBean.ResultBean> beans;
    private CustomSearchView cvs;


    @Override
    protected BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        xbn = findViewById(R.id.xbn);
        rc_h = findViewById(R.id.rc_h);
        rc_v = findViewById(R.id.rc_v);
        rc_g = findViewById(R.id.rc_g);
        cvs = findViewById(R.id.search);

        //点击事件
        cvs.setOnSearchClick(new CustomSearchView.OnSearchClick() {
            @Override
            public void onSerachCilck(String str) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("searchKey",str);
                startActivity(intent);
            }
        });

        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rc_v.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rc_h.setLayoutManager(layoutManager1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rc_g.setLayoutManager(gridLayoutManager);
    }
    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof HomePagePresenter){
            ((HomePagePresenter) presenter).getBanner("http://mobile.bwstudent.com/small/commodity/v1/bannerShow");
            ((HomePagePresenter) presenter).getList("http://mobile.bwstudent.com/small/commodity/v1/commodityList");
        }
    }

    @Override
    public void getBannerSuccess(String str) {
        Gson gson = new Gson();
        final XbanBean bean = gson.fromJson(str, XbanBean.class);
        beans = bean.getResult();

        xbn.setBannerData(beans);
        xbn.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                XbanBean.ResultBean resultBean = beans.get(position);
                String imageUrl = resultBean.getImageUrl();
                Glide.with(MainActivity.this).load(imageUrl).into((ImageView) view);
            }
        });
        xbn.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void getBannerFailure(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getListSuccess(String str) {
        Gson gson = new Gson();
        ListBean bean = gson.fromJson(str, ListBean.class);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, bean.getResult().getRxxp().getCommodityList());
        rc_h.setAdapter(adapter);
        adapter.setOnItemClick(new RecyclerViewAdapter.OnItemClick() {
            @Override
            public void OnItemClick(int id) {
                Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerViewAdapterTwo adapterTwo = new RecyclerViewAdapterTwo(this, bean.getResult().getMlss().getCommodityList());
        rc_v.setAdapter(adapterTwo);
        adapterTwo.setOnItemClick(new RecyclerViewAdapterTwo.OnItemClick() {
            @Override
            public void onItemClick(int id) {
                Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });



        RecyclerViewAdapterThree adapterThree = new RecyclerViewAdapterThree(this, bean.getResult().getPzsh().getCommodityList());
        rc_g.setAdapter(adapterThree);
        adapter.setOnItemClick(new RecyclerViewAdapter.OnItemClick() {
            @Override
            public void OnItemClick(int id) {
                Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void getListError(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();

    }
}
