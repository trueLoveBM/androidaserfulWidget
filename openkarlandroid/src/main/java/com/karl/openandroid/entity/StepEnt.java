package com.karl.openandroid.entity;

import java.util.List;

public abstract class StepEnt<T> {

    public static final int STATE_UN_DO = 0;
    public static final int STATE_UN_FINISH = 1;
    public static final int STATE_FINISHED = 2;

    public static final int NODETYPE_FIRST_NODE = 0;
    public static final int NODETYPE_NORMAL = 1;
    public static final int NODETYPE_END_NODE = 2;

    private T tag;

    /**
     * 节点类型
     */
    private int stateType;

    /**
     * 显示文字(标题)
     */
    private String text;

    private int nodeType;

    /**
     * 显示文字（内容）
     */
    private String content;


    public StepEnt(T tag, int nodeType) {
        this.tag = tag;
        text = initText();
        stateType = initStateType();
        this.nodeType = nodeType;
        this.content=initContent();
    }

    /**
     * 获取显示内容
     * @return
     */
    protected abstract String initContent();


    public void setTag(T tag) {
        this.tag = tag;
    }

    /**
     * 获取显示文字
     *
     * @return
     */
    public abstract String initText();

    public abstract int initStateType();

    public void setStateType(int stateType) {
        this.stateType = stateType;
    }

    public T getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public int getStateType() {
        return stateType;
    }

    public String getText() {
        return text;
    }

    public int getNodeType() {
        return nodeType;
    }

}
