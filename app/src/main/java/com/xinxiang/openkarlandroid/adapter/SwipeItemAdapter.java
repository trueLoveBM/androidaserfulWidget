package com.xinxiang.openkarlandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karl.openandroid.widget.SwipeItemLayout;
import com.xinxiang.openkarlandroid.R;

import java.util.List;

public class SwipeItemAdapter extends RecyclerView.Adapter<SwipeItemAdapter.ViewHolder> {

    private List<String> datas;

    public SwipeItemAdapter(List<String> datas) {
        this.datas = datas;
    }

    OnItemClickListener onItemClickListener;
    OnMenuClickListener onMenuClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        if (onItemClickListener != null) {
            viewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick((String) v.getTag());
                    viewHolder.swipeItemLayout.close();
                }
            });
        }
        if (onMenuClickListener != null) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMenuClickListener.onMenuClick(v.getId(), (String) v.getTag());
                    viewHolder.swipeItemLayout.close();
                }
            };
            viewHolder.tv_flag.setOnClickListener(onClickListener);
            viewHolder.tv_details.setOnClickListener(onClickListener);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_display_text.setText(datas.get(i));
        viewHolder.root.setTag(datas.get(i));
        viewHolder.tv_details.setTag(datas.get(i));
        viewHolder.tv_flag.setTag(datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SwipeItemLayout swipeItemLayout;
        LinearLayout root;
        TextView tv_flag;
        TextView tv_details;
        TextView tv_display_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            tv_flag = itemView.findViewById(R.id.tv_flag);
            tv_details = itemView.findViewById(R.id.tv_details);
            tv_display_text = itemView.findViewById(R.id.tv_display_text);
            swipeItemLayout= itemView.findViewById(R.id.SwipeItemLayout);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String s);
    }

    public interface OnMenuClickListener {
        void onMenuClick(int menuId, String s);
    }
}
