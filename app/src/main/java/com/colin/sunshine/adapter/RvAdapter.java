package com.colin.sunshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.colin.sunshine.R;
import com.colin.sunshine.model.MoyuListBean;

import java.util.List;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private Context mContext;
    private List<MoyuListBean> mDatas;

    public RvAdapter(Context context, List<MoyuListBean> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.moyu_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.tv_moyu_item_nick_name.setText(mDatas.get(position));
        holder.tv_moyu_item_nick_name.setText(mDatas.get(position).nickname);
        holder.tv_moyu_item_content.setText(mDatas.get(position).content);
        holder.tv_moyu_item_desc.setText("来自" +mDatas.get(position).company + "  " + mDatas.get(position).createTime);

        Glide.with(mContext)
                .load(mDatas.get(position).avatar)
                // 将头像剪裁成圆形
                .circleCrop()
                .error(R.mipmap.ic_default_avatar)
                // 不使用内存缓存头像
                .skipMemoryCache(true)
                // 不使用磁盘缓存头像
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.iv_moyu_item_avatar);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_moyu_item_nick_name;

        private TextView tv_moyu_item_desc;

        private TextView tv_moyu_item_content;

        private RecyclerView simple_grid_rvlayout;
        private ImageView iv_moyu_item_avatar;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_moyu_item_nick_name = itemView.findViewById(R.id.tv_moyu_item_nick_name);

            tv_moyu_item_desc = itemView.findViewById(R.id.tv_moyu_item_desc);

            tv_moyu_item_content = itemView.findViewById(R.id.tv_moyu_item_content);

            simple_grid_rvlayout = itemView.findViewById(R.id.simple_grid_rvlayout);
            iv_moyu_item_avatar = itemView.findViewById(R.id.iv_moyu_item_avatar);


        }
    }
}
