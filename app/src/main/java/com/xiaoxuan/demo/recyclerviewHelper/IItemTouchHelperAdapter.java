package com.xiaoxuan.demo.recyclerviewHelper;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public interface IItemTouchHelperAdapter
{
    /**
     * 当item被移动时调用
     *
     * @param fromPosition item的起点
     * @param toPosition item的终点
     */
    void onItemMove(int fromPosition, int toPosition);
    
    /**
     * 当item被侧滑时调用
     *
     * @param position 被侧滑的item的position
     */
    void onItemDismiss(int position);
    
    void onItemMoveFinish(int position);
}
