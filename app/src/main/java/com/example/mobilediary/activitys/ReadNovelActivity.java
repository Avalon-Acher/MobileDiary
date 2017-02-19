package com.example.mobilediary.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private Novel novel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_novel);

        Intent intent=getIntent();
        chapter=intent.getStringExtra("chapter");
        name=intent.getStringExtra("name");
        author=intent.getStringExtra("author");

        Log.d("ReadNovelActivity",chapter+name+author);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        initView();




    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Novel> novels= DataSupport.where("chapter=? and name=? and author=?",chapter,name,author).find(Novel.class);
        novel=novels.get(0);
        view_chapter.setText(String.valueOf(novel.getChapter()));
        view_title.setText(novel.getTitle());
        view_content.setText(novel.getConnect());
    }

    private void initView(){
        view_chapter=(TextView)findViewById(R.id.read_novel_chapter);
        view_title=(TextView)findViewById(R.id.read_novel_title);
        view_content=(TextView)findViewById(R.id.read_novel_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_novel_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.write:
                Intent intent=new Intent(ReadNovelActivity.this,WriteNovelActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("novel",novel);
                intent.putExtras(bundle);
                Log.d("ReadNovelActivity","Read startActivity");
                startActivity(intent);
                break;
        }
        return true;
    }
}
