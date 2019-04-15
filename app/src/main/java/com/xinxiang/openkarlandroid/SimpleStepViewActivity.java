package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.karl.openandroid.adapter.StepAdapter;
import com.karl.openandroid.entity.StepEnt;
import com.karl.openandroid.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class SimpleStepViewActivity extends AppCompatActivity {

    RecyclerView rvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_step_view);
        rvDemo = findViewById(R.id.rv_demo);

        List<ApprovalStepEnt> data = new ArrayList<>();

        ApprovalEnt ent = new ApprovalEnt();
        ent.status = ApprovalEnt.STATUS_AGREE;
        ent.approvalName = "张一";
        ent.approvalContent = "发起审批";
        data.add(new ApprovalStepEnt(ent, StepEnt.NODETYPE_FIRST_NODE));


        ApprovalEnt ent1 = new ApprovalEnt();
        ent1.status = ApprovalEnt.STATUS_AGREE;
        ent1.approvalName = "张二";
        ent1.approvalContent = "同意了";
        data.add(new ApprovalStepEnt(ent1, StepEnt.NODETYPE_NORMAL));


        ApprovalEnt ent2 = new ApprovalEnt();
        ent2.status = ApprovalEnt.STATUS_REJECT;
        ent2.approvalName = "张三";
        ent2.approvalContent = "驳回了";
        data.add(new ApprovalStepEnt(ent2, StepEnt.NODETYPE_NORMAL));


        ApprovalEnt ent3 = new ApprovalEnt();
        ent3.status = ApprovalEnt.STATUS_UN_DO;
        ent3.approvalName = "张四";
        ent3.approvalContent = "未审批";
        data.add(new ApprovalStepEnt(ent3, StepEnt.NODETYPE_END_NODE));

        StepAdapter<ApprovalStepEnt> adapter = new StepAdapter<>(data);
        rvDemo.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDemo.setLayoutManager(linearLayoutManager);

    }


    public static void showActivity(Context context){
        Intent intent=new Intent(context,SimpleStepViewActivity.class);
        context.startActivity(intent);
    }

    /**
     * 审批实体
     */
    class ApprovalEnt {

        public static final int STATUS_UN_DO = 1;
        public static final int STATUS_AGREE = 2;
        public static final int STATUS_REJECT = 3;

        /**
         * 审批状态
         */
        private int status;

        /**
         * 审批名称
         */
        private String approvalName;

        /**
         * 审批内容
         */
        private String approvalContent;
    }


    static class ApprovalStepEnt extends StepEnt<ApprovalEnt> {

        public ApprovalStepEnt(ApprovalEnt tag, int nodeType) {
            super(tag, nodeType);
        }

        @Override
        protected String initContent() {
            return getTag().approvalContent;
        }

        @Override
        public String initText() {
            return getTag().approvalName;
        }

        @Override
        public int initStateType() {
            int State = 0;
            switch (getTag().status) {
                case ApprovalEnt.STATUS_AGREE:
                    State = StepEnt.STATE_FINISHED;
                    break;
                case ApprovalEnt.STATUS_REJECT:
                    State = StepEnt.STATE_UN_FINISH;
                    break;
                case ApprovalEnt.STATUS_UN_DO:
                default:
                    State = StepEnt.STATE_UN_DO;
                    break;
            }
            return State;
        }


    }

//    static class StepDemoAdapter extends StepAdapter<>
}
