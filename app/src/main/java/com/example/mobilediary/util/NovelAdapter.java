package com.example.mobilediary.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilediary.ActivityCollector;
import com.example.mobilediary.R;
import com.example.mobilediary.activitys.ReadNovelActivity;
import com.example.mobilediary.db.Novel;

import java.util.List;

/**
 * Created by 连浩逵 on 2017/2/17.
 */

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.ViewHolder> {

    private List<Novel> novels;

    public NovelAdapter(List<Novel> novels ){
        this.novels=novels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.novelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子Item点击事件
                Activity activity= ActivityCollector.activitys.get(ActivityCollector.activitys.size()-1);
                Intent intent=new Intent(activity, ReadNovelActivity.class);
                intent.putExtra("chapter",holder.chapter);
                intent.putExtra("name",holder.name);
                intent.putExtra("author",holder.author);
                activity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Novel novel=novels.get(position);
        holder.novelName.setText(novel.getName());
        holder.novelTitle.setText(novel.getTitle());
        holder.novelReadDate.setText(novel.getReadDate());
        holder.novelAlterDate.setText(novel.getAlterDate());
        holder.chapter=String.valueOf(novel.getChapter());
        holder.name=novel.getName();
        holder.author=novel.getAuthor();
    }

    @Override
    public int getItemCount() {
        return novels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView novelName;
        TextView novelTitle;
        TextView novelReadDate;
        TextView novelAlterDate;
        View novelView;
        String chapter;
        String name;
        String author;
        public ViewHolder(View itemView) {
            super(itemView);
            novelView=itemView;
            novelName=(TextView)itemView.findViewById(R.id.novel_item_name);
            novelTitle=(TextView)itemView.findViewById(R.id.novel_item_title);
            novelReadDate=(TextView)itemView.findViewById(R.id.novel_item_read_date);
            novelAlterDate=(TextView)itemView.findViewById(R.id.novel_item_alter_date);
        }
    }
}
