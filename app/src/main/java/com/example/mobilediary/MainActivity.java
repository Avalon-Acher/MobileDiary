package com.example.mobilediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobilediary.activitys.CreateNovelBookActivity;
import com.example.mobilediary.db.NovelBook;
import com.example.mobilediary.presenter.MainActivityPresenter;
import com.example.mobilediary.presenterCompl.MainActivityPresenterCompl;
import com.example.mobilediary.util.NovelBookAdapter;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;


public class MainActivity extends BaseActivity implements MainActivityPresenter{
    private MainActivityPresenterCompl presenterCompl;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private NovelBookAdapter adapter;
    private List<NovelBook> novelBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterCompl=new MainActivityPresenterCompl(this);//逻辑处理器
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //悬浮按钮
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, CreateNovelBookActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        novelBooks= DataSupport.findAll(NovelBook.class);
        adapter=new NovelBookAdapter(novelBooks);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main","onResume");
        novelBooks.clear();
        List<NovelBook>books= DataSupport.findAll(NovelBook.class);
        for(NovelBook novelBook:books){
            novelBooks.add(novelBook);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.delete:
                Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
