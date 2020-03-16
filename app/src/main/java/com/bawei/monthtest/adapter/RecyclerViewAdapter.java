package com.bawei.monthtest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.monthtest.R;
import com.bawei.monthtest.activity.MainActivity;
import com.bawei.monthtest.bean.ListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * RecyclerViewAdapter 适配器
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList;

    public RecyclerViewAdapter(Context context, List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item1, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).tv_name.setText(commodityList.get(position).getCommodityName());
        ((ViewHolder)holder).tv_price.setText("¥"+commodityList.get(position).getPrice()+"");
        Glide.with(context).load(commodityList.get(position).getMasterPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((ViewHolder) holder).iv);
        ((ViewHolder)holder).ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClick!=null){
                    mOnItemClick.OnItemClick(commodityList.get(position).getCommodityId());
                }

            }
        });
    }

    private OnItemClick mOnItemClick;
    public void  setOnItemClick(OnItemClick onItemClick){
        mOnItemClick=onItemClick;
    }

    public interface OnItemClick{
        void OnItemClick(int id);
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv_name;
        private TextView tv_price;
        private  LinearLayout ll1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_price=itemView.findViewById(R.id.tv_price);
            ll1 = itemView.findViewById(R.id.ll1);
        }
    }
}
