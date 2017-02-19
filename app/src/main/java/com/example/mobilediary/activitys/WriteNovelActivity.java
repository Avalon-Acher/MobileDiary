package com.example.mobilediary.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.db.Novel;

import static android.R.attr.id;

/**
 * Created by 连浩逵 on 2017/2/18.
 */

public class WriteNovelActivity extends BaseActivity {
    private Novel novel;
    private Toolbar toolbar;
    private TextView chapter;
    private TextView title;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_novel);

        Log.d("WriteNovelActivity","How");

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        novel= (Novel) bundle.getSerializable("novel");
        initView();

        chapter.setText(String.valueOf(novel.getChapter()));
        title.setText(novel.getTitle());
        content.setText(novel.getConnect());
    }

    private void initView(){
        chapter=(TextView)findViewById(R.id.write_novel_chapter);
        title=(TextView)findViewById(R.id.write_novel_title);
        content=(EditText)findViewById(R.id.write_novel_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_novel_chapter_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.save:
                if(content.getText()!=null){
                    novel.setConnect(content.getText().toString());
                    novel.updateAll("name=? and author=? and chapter=?",novel.getName(),novel.getAuthor(),String.valueOf(novel.getChapter()));
                }else{
                    Toast.makeText(this,"修改内容不得为空",Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
        }
        return true;
    }
}
