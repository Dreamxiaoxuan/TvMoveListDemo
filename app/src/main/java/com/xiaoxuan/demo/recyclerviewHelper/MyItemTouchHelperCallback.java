package com.xiaoxuan.demo.recyclerviewHelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback
{
    private IItemTouchHelperAdapter mAdapter;
    
    public MyItemTouchHelperCallback(IItemTouchHelperAdapter mAdapter)
    {
        this.mAdapter = mAdapter;
    }
    
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        // 上下拖拽
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 向右侧滑
        int swipeFlags = ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }
    
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        // 滑动监听 右滑
        // 屏蔽后右滑删除取消
        // mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
    
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState)
    {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
        {
            if (viewHolder instanceof IItemTouchHelperViewHolder)
            {
                IItemTouchHelperViewHolder itemTouchHelperViewHolder = (IItemTouchHelperViewHolder)viewHolder;
                itemTouchHelperViewHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
    
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof IItemTouchHelperViewHolder)
        {
            IItemTouchHelperViewHolder itemTouchHelperViewHolder = (IItemTouchHelperViewHolder)viewHolder;
            itemTouchHelperViewHolder.onItemClear();
            mAdapter.onItemMoveFinish(viewHolder.getAdapterPosition());
        }
    }
    
    @Override
    public boolean isItemViewSwipeEnabled()
    {
        // 设置是否可以右滑
        return false;
    }
    
    @Override
    public boolean isLongPressDragEnabled()
    {
        return false;
    }
}
