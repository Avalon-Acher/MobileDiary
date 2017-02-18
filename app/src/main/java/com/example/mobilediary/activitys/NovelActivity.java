package com.example.mobilediary.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.db.Novel;
import com.example.mobilediary.util.NovelAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/17.
 */

public class NovelActivity extends BaseActivity{

    private RecyclerView recyclerView;
    private NovelAdapter adapter;
    private List<Novel> novels;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private String novelBookName,novelBookAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);

        Intent intent=getIntent();
        novelBookName=intent.getStringExtra("novelBookName");
        novelBookAuthor=intent.getStringExtra("novelBookAuthor");

        toolbar=(Toolbar)findViewById(R.id.novel_toolbar);
        setSupportActionBar(toolbar);

        fab=(FloatingActionButton)findViewById(R.id.novel_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建章节
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.novel_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        novels= DataSupport.where("name=? and author=?",novelBookName,novelBookAuthor).find(Novel.class);
        adapter=new NovelAdapter(novels);
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        novels.clear();
        List<Novel> chapters=DataSupport.where("name=? and author=?",novelBookName,novelBookAuthor).find(Novel.class);
        for(Novel novel:chapters)
            novels.add(novel);
        adapter.notifyDataSetChanged();
    }
}
