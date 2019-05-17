package com.xiaoxuan.demo.recyclerviewHelper;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public interface IItemTouchHelperViewHolder
{
    // item被选中，在侧滑或拖拽过程中更新状态
    void onItemSelected();
    
    // item的拖拽或侧滑结束，恢复默认的状态
    void onItemClear();
}
