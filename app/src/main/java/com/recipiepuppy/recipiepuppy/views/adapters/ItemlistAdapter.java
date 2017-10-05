package com.recipiepuppy.recipiepuppy.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recipiepuppy.recipiepuppy.R;
import com.recipiepuppy.recipiepuppy.model.ItemModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sumit on 4/10/17.
 */

public class ItemlistAdapter extends RecyclerView.Adapter<ItemlistAdapter.ViewHolder> {
    private ArrayList<ItemModel.Result> list;
    private Context context;

    public ItemlistAdapter(ArrayList<ItemModel.Result> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (LayoutInflater.from(context)).inflate(R.layout.adapter, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getThumbnail()).into(holder.ivImage);
        if (!TextUtils.isEmpty(list.get(position).getTitle()))
            holder.tvHeading.setText(list.get(position).getTitle());
        if (!TextUtils.isEmpty(list.get(position).getIngredients()))
            holder.tvTexting.setText(list.get(position).getIngredients());
        if (!TextUtils.isEmpty(list.get(position).getHref()))
            holder.tvTexturl.setText(list.get(position).getHref());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvHeading)
        TextView tvHeading;
        @BindView(R.id.tvTexting)
        TextView tvTexting;
        @BindView(R.id.tvTexturl)
        TextView tvTexturl;
        @BindView(R.id.relLayout)
        RelativeLayout relLayout;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
