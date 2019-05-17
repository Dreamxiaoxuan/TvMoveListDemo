package com.xiaoxuan.demo.recyclerviewHelper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public interface OnStartDragListener
{
    /**
     * 当View需要拖拽时回调
     *
     * @param viewHolder The holder of view to drag
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
