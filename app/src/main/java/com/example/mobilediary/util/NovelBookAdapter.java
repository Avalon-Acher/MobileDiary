package com.example.mobilediary.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilediary.ActivityCollector;
import com.example.mobilediary.BaseActivity;
import com.example.mobilediary.R;
import com.example.mobilediary.activitys.NovelActivity;
import com.example.mobilediary.db.NovelBook;
import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/16.
 */
public class NovelBookAdapter extends RecyclerView.Adapter<NovelBookAdapter.ViewHolder> {

    private List<NovelBook> novelBooks;


    public NovelBookAdapter(List<NovelBook> novelBooks){
        this.novelBooks=novelBooks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_book_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.novelBookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity=ActivityCollector.activitys.get(ActivityCollector.activitys.size()-1);
                Intent intent=new Intent(activity, NovelActivity.class);
                intent.putExtra("novelBookName",holder.novelBookName.getText().toString());
                intent.putExtra("novelBookAuthor",holder.novelBookAuthor.getText().toString());
                activity.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NovelBook novelBook=novelBooks.get(position);
        holder.novelBookImage.setImageResource(R.mipmap.ic_launcher);
        holder.novelBookName.setText(novelBook.getName());
        holder.novelBookAuthor.setText(novelBook.getAuthor());
        holder.novelBookSummary.setText(novelBook.getSummary());

    }

    @Override
    public int getItemCount() {
        return novelBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView novelBookImage;
        TextView novelBookName;
        TextView novelBookAuthor;
        TextView novelBookSummary;
        View novelBookView;
        public ViewHolder(View itemView) {
            super(itemView);
            novelBookView=itemView;
            novelBookImage=(ImageView)itemView.findViewById(R.id.novel_book_image);
            novelBookName=(TextView)itemView.findViewById(R.id.novel_book_item_name);
            novelBookAuthor=(TextView)itemView.findViewById(R.id.novel_book_item_author);
            novelBookSummary=(TextView)itemView.findViewById(R.id.novel_book_item_summary);
        }
    }
}
