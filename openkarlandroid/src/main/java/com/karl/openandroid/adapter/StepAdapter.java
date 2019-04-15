package com.karl.openandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karl.openandroid.entity.StepEnt;
import com.karl.openandroid.widget.StepView;
import com.xinxiang.openkarlandroid.R;

import java.util.List;

/**
 * stepView的简单适配器
 * 包含一个圆圈节点，标题，和文字
 * author Fan.Huang
 * eamil  15209187329@qq.com
 * created 2019/4/15 10:57
 */
public class StepAdapter<T extends StepEnt> extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private List<T> mDatas;

    public StepAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_step, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        StepEnt<T> ent = mDatas.get(i);
        viewHolder.stepView.setText(ent.getText());
        viewHolder.stepView.setNodeType(ent.getNodeType());
        viewHolder.stepView.setStepState(ent.getStateType());
        viewHolder.stepView.setContent(ent.getContent());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder<E> extends RecyclerView.ViewHolder {

        StepView stepView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepView = itemView.findViewById(R.id.stepview);
        }
    }
}
