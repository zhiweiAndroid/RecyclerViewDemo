package com.example.shundai.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRelv;
    private MainAdapter mAdapter;
    private List<News> mDatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelv = (RecyclerView) findViewById(R.id.rlv);
        mRelv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRelv.setLayoutManager(manager);
        mRelv.setAdapter(mAdapter=new MainAdapter(this,mDatas));
        for (int i = 0; i < 20; i++) {
            if (i==5){
                mDatas.add(new News(1,"这是图片"));
            }else {
                mDatas.add(new News(0,"这是我的第"+i+"条数据"));
            }
        }

        mAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Toast.makeText(MainActivity.this, "点击事件"+position, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnItemLongClickListener(new MainAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position, View view, RecyclerView.ViewHolder vh) {
                Toast.makeText(MainActivity.this, "长按事件"+position, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
