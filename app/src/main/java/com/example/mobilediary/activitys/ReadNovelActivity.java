package com.example.mobilediary.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.db.Novel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/18.
 */

public class ReadNovelActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView view_chapter;
    private TextView view_title;
    private TextView view_content;
    private String chapter,name,author;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_novel);

        Intent intent=getIntent();
        chapter=intent.getStringExtra("chapter");
        name=intent.getStringExtra("name");
        author=intent.getStringExtra("author");

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();




    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Novel> novels= DataSupport.where("chapter=? and name=? and author=?",chapter,name,author).find(Novel.class);
        Novel novel=novels.get(0);
        view_chapter.setText(novel.getChapter());
        view_title.setText(novel.getTitle());
        view_content.setText(novel.getConnect());
    }

    private void initView(){
        view_chapter=(TextView)findViewById(R.id.read_novel_chapter);
        view_title=(TextView)findViewById(R.id.read_novel_title);
        view_content=(TextView)findViewById(R.id.read_novel_content);
    }

}
