package com.it.hungvt.retrofitdemo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it.hungvt.retrofitdemo.data.model.Item;
import com.it.hungvt.retrofitdemo.data.model.SOAnswerResponse;

import java.util.List;

/**
 * Created by Administrator on 10/28/2017.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>{

    private Context context;
    private List<Item> items;
    private PostItemListener listener;
    private LayoutInflater inflater;

    public AnswerAdapter(Context context, List<Item> items, PostItemListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        return new ViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.txtTitle.setText(item.getOwner().getDisplayName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private PostItemListener listener;
        private TextView txtTitle;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            this.listener = listener;
            txtTitle = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(v->{
               Item item = items.get(getAdapterPosition());
               listener.onPostClick(item.getAnswerId());
               notifyDataSetChanged();
            });

        }
    }
    public void updateAnswer(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public interface PostItemListener{
        public void onPostClick(long id);
    }
}
