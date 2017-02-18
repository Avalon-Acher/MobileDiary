package com.example.mobilediary.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.db.NovelBook;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/16.
 */
public class CreateNovelBookActivity extends BaseActivity {
    private EditText novelName;
    private EditText novelAuthor;
    private EditText novelSummary;
    private Button novelPreserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_novel_book);
        initView();

        novelPreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novelBookName;
                String novelBookAuthor;
                if (checkEditText(novelName) && checkEditText(novelAuthor)) {
                    novelBookName = novelName.getText().toString();
                    novelBookAuthor = novelAuthor.getText().toString();
                    List<NovelBook> books = DataSupport.where("name=? and author=?", novelBookName, novelBookAuthor).find(NovelBook.class);
                    if (books != null && books.size() != 0) {
                        Toast.makeText(CreateNovelBookActivity.this, "该小说已经存在", Toast.LENGTH_SHORT).show();
                    } else {
                        NovelBook book = new NovelBook();
                        book.setName(novelBookName);
                        book.setAuthor(novelBookAuthor);
                        book.setChapterCount(0);
                        if (novelSummary.getText() != null)
                            book.setSummary(novelSummary.getText().toString());
                        else
                            book.setSummary("");
                        book.save();
                        CreateNovelBookActivity.this.finish();
                    }
                }

            }

        });
    }

    private void initView(){
        novelName=(EditText)findViewById(R.id.novel_name);
        novelAuthor=(EditText)findViewById(R.id.novel_author);
        novelSummary=(EditText)findViewById(R.id.novel_summary);
        novelPreserve=(Button)findViewById(R.id.novel_preserve);
    }

    private boolean checkEditText(EditText editText){
        String content;
        if(editText.getText()!=null&&!editText.getText().toString().equals("")){
            content=editText.getText().toString();
            if(content.contains(" ")){
                Toast.makeText(CreateNovelBookActivity.this,"不能含有空格等字符",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }else{
            Toast.makeText(CreateNovelBookActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}