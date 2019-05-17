package com.xiaoxuan.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.xiaoxuan.demo.recyclerviewHelper.MyItemTouchHelperCallback;
import com.xiaoxuan.demo.recyclerviewHelper.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoxuan on 2018/5/15.
 * QQ群号：257251953
 * 专注Android Tv 开发的社区
 */
public class MainActivity extends AppCompatActivity implements OnStartDragListener
{
    private ItemTouchHelper mItemTouchHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rcv_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        list.add("Android Tv");
        list.add("QQ群号：257251953");
        list.add("专注Android Tv 开发的社区");
        list.add("作者：小轩");
        final OrderAdapter adapter = new OrderAdapter(list, this);
        adapter.setonItemMoveFinishListener(new OrderAdapter.onItemMoveFinishListener()
        {
            @Override
            public void onItemMoveFinish(List<String> list, int position)
            {
                // 移动后获取排列列表
            }
        });
        adapter.setonItemDeleteListener(new OrderAdapter.onItemDeleteListener()
        {
            @Override
            public void onItemDelete(View view, int position)
            {
                // 删除某一行回调监听
            }
        });
        recyclerView.setAdapter(adapter);
        mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
    
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder)
    {
        // 通知ItemTouchHelper开始拖拽
        mItemTouchHelper.startDrag(viewHolder);
    }
}
