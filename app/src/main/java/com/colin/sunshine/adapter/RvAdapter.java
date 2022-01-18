package com.colin.sunshine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
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

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_moyu_item_nick_name;
        private TextView tv_moyu_item_content;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_moyu_item_nick_name = itemView.findViewById(R.id.tv_moyu_item_nick_name);
            tv_moyu_item_content = itemView.findViewById(R.id.tv_moyu_item_content);

        }
    }
}
