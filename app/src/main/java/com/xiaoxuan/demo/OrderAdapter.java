package com.xiaoxuan.demo;

import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaoxuan.demo.recyclerviewHelper.IItemTouchHelperAdapter;
import com.xiaoxuan.demo.recyclerviewHelper.IItemTouchHelperViewHolder;
import com.xiaoxuan.demo.recyclerviewHelper.OnStartDragListener;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ItemViewHolder> implements IItemTouchHelperAdapter
{
    private List<String> mList;
    
    private final OnStartDragListener mDragListener;
    
    private onItemMoveFinishListener mOnItemMoveFinishListener;
    
    private onItemDeleteListener mOnItemDeleteListener;
    
    public OrderAdapter(List<String> list, OnStartDragListener mDragListener)
    {
        mList = list;
        this.mDragListener = mDragListener;
    }
    
    public void setList(List<String> list)
    {
        this.mList = list;
    }
    
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordered_list_item, parent, false);
        return new ItemViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, @SuppressLint("RecyclerView")
    final int position)
    {
        holder.tvThemeNum.setTextColor(Color.rgb(255, 204, 0));
        holder.tvThemeNum.addOuterShadow(20, 3, 3, Color.argb(102, 215, 5, 0));
        holder.tvThemeNum.setText(mList.get(position));
        holder.itemView.setEnabled(false);
        holder.ivThemeTop.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    mDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }
    
    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }
    
    @Override
    public void onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
    
    @Override
    public void onItemDismiss(int position)
    {
        mList.remove(position);
        notifyItemRemoved(position);
    }
    
    @Override
    public void onItemMoveFinish(int position)
    {
        if (mOnItemMoveFinishListener != null)
        {
            if (mList != null && mList.size() > 0)
            {
                mOnItemMoveFinishListener.onItemMoveFinish(mList, position);
                notifyDataSetChanged();
            }
        }
    }
    
    class ItemViewHolder extends RecyclerView.ViewHolder implements IItemTouchHelperViewHolder
    {
        private ImageView ivThemeTop;
        
        private MagicTextView tvThemeNum;
        
        ItemViewHolder(View itemView)
        {
            super(itemView);
            ivThemeTop = itemView.findViewById(R.id.iv_item_theme_top);
            tvThemeNum = itemView.findViewById(R.id.tv_item_theme_num);
        }
        
        @Override
        public void onItemSelected()
        {
            itemView.setTranslationZ(10);
        }
        
        @Override
        public void onItemClear()
        {
            itemView.setTranslationZ(0);
        }
    }
    
    public void setonItemMoveFinishListener(onItemMoveFinishListener onItemMoveFinishListener)
    {
        mOnItemMoveFinishListener = onItemMoveFinishListener;
    }
    
    public void setonItemDeleteListener(onItemDeleteListener onItemMoveFinishListener)
    {
        mOnItemDeleteListener = onItemMoveFinishListener;
    }
    
    public interface onItemMoveFinishListener
    {
        void onItemMoveFinish(List<String> list, int position);
    }
    
    public interface onItemDeleteListener
    {
        void onItemDelete(View view, int position);
    }
}
