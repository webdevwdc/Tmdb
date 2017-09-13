package com.wts.wts.modules.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wts.wts.R;
import com.wts.wts.mvp.model.home.MoviesResults;

import java.util.List;

/**
 * Created by android on 13/9/17.
 */

public class MoviesDateLimitAdapter extends RecyclerView.Adapter<MoviesDateLimitAdapter.MyViewHolder> {

    Context mContext;
    List<MoviesResults> al_movies;
    private MyViewHolder holder;

    public MoviesDateLimitAdapter(Context mContext, List<MoviesResults>  al_movies) {
        this.al_movies=al_movies;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);
        holder=new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txt_name.setText(al_movies.get(position).getTitle());
        setImage(holder.img_movies,al_movies.get(position).getPosterPath());

    }

    @Override
    public int getItemCount() {
        return al_movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_movies;
        TextView txt_name;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            img_movies=(ImageView)itemView.findViewById(R.id.img_movies);
        }
    }

    private void setImage(ImageView view,String full_img_url){
        Glide.with(view.getContext()).load("http://image.tmdb.org/t/p/w780/"+full_img_url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(view);
    }
}
