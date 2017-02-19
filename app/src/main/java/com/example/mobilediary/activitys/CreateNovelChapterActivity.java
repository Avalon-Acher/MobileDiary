package com.example.mobilediary.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.db.Novel;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/18.
 */

public class CreateNovelChapterActivity extends BaseActivity {

    private TextView view_name;
    private TextView view_author;
    private EditText view_chapter;
    private EditText view_title;
    private EditText view_content;
    private Toolbar toolbar;
    private String name;
    private String author;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_novel_chapter);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        initView();
        Intent intent=getIntent();
        name=intent.getStringExtra("novelBookName");
        author=intent.getStringExtra("novelBookAuthor");
        view_name.setText(name);
        view_author.setText(author);
    }

    private void initView(){
        view_name=(TextView)findViewById(R.id.create_novel_name);
        view_author=(TextView)findViewById(R.id.create_novel_author);
        view_chapter=(EditText)findViewById(R.id.create_novel_chapter);
        view_title=(EditText)findViewById(R.id.create_novel_title);
        view_content=(EditText)findViewById(R.id.create_novel_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_novel_chapter_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                if(checkEditText(view_chapter)&&checkEditText(view_title)&&view_content.getText()!=null){

                    List<Novel> list= DataSupport.where("chapter=? and name=? and author=?",view_chapter.getText().toString(),name,author).find(Novel.class);
                    if(list!=null&&list.size()!=0){
                        Toast.makeText(this,"章节已经存在，请重新输入",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Novel novel=new Novel();
                    novel.setName(name);
                    novel.setAuthor(author);
                    novel.setChapter(Integer.valueOf(view_chapter.getText().toString()));
                    novel.setTitle(view_title.getText().toString());
                    novel.setConnect(view_content.getText().toString());
                    novel.setConnectCount(novel.getConnect().length());
                    //获取当前系统时间进行添加
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    String date=simpleDateFormat.format(new Date());
                    novel.setAlterDate(date);
                    novel.setReadDate(date);
                    novel.save();
                    this.finish();
                }
                break;
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }

    private boolean checkEditText(EditText editText){
        String content;
        if(editText.getText()!=null&&!editText.getText().toString().equals("")){
            content=editText.getText().toString();
            if(content.contains(" ")){
                Toast.makeText(CreateNovelChapterActivity.this,"不能含有空格等字符",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }else{
            Toast.makeText(CreateNovelChapterActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
