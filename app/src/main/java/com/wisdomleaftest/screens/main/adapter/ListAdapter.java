package com.wisdomleaftest.screens.main.adapter;

import android.content.Context;
import android.net.Uri;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wisdomleaftest.R;
import com.wisdomleaftest.screens.main.IListPresenter;
import com.wisdomleaftest.screens.main.ListActivity;
import com.wisdomleaftest.screens.main.model.Datum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final List<Datum> list;
    private Context context;
    IListPresenter iListPresenter;

    public ListAdapter(IListPresenter iListPresenter, ListActivity listActivity) {
        this.list = new ArrayList<>();
        this.context = listActivity;
        this.iListPresenter = iListPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (list.size() != 0) {
            final Datum model = list.get(position);
            holder.title.setText(model.getAuthor());
            Picasso.with(context).load(model.getDownloadUrl()).into(holder.imageView);

           //holder.imageView.setImageURI(Uri.parse(model.getUrl()));

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Datum> model) {
        list.clear();
        list.addAll((Collection<? extends Datum>) model);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title;
        AppCompatImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            imageView =itemView.findViewById(R.id.image);
        }
    }
}
