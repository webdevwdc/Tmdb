package com.wts.wts.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wts.wts.R;
import com.wts.wts.base.BaseActivity;
import com.wts.wts.di.component.DaggerHomeComponent;
import com.wts.wts.di.modules.HomeModule;
import com.wts.wts.modules.home.adapter.MoviesDateLimitAdapter;
import com.wts.wts.modules.home.adapter.MoviesPopularityAdapter;
import com.wts.wts.modules.home.adapter.MoviesTopRatedAdapter;
import com.wts.wts.mvp.model.home.MoviesResults;
import com.wts.wts.mvp.presenter.HomePresenter;
import com.wts.wts.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class HomeActivity extends BaseActivity implements HomeView {

    MoviesDateLimitAdapter moviesDateLimitAdapter;
    MoviesPopularityAdapter moviesPopularityAdapter;
    MoviesTopRatedAdapter moviesTopRatedAdapter;
    LinearLayoutManager mLayoutManager;

    @Bind(R.id.recyclerView_date) RecyclerView recyclerView;
    @Bind(R.id.recyclerView_popularity) RecyclerView recyclerView_popularity;
    @Bind(R.id.recyclerView_top_rated) RecyclerView recyclerView_top_rated;

    List<MoviesResults> al_movies_date,al_movies_popularity,al_movies_top_rated;

    @Inject
    HomePresenter mMoviesPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        injectDependency();
        initializeMoviesList();
        mMoviesPresenter.getMoviesByDate();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    private void injectDependency(){
        DaggerHomeComponent.builder().applicationComponent(getApplicationCOmponent())
                .homeModule(new HomeModule(this)).build().inject(this);
    }

    private void initializeMoviesList(){
        al_movies_date =new ArrayList<>();
        al_movies_popularity=new ArrayList<>();
        al_movies_top_rated=new ArrayList<>();

        moviesDateLimitAdapter =new MoviesDateLimitAdapter(this, al_movies_date);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesDateLimitAdapter);

        moviesPopularityAdapter =new MoviesPopularityAdapter(this, al_movies_popularity);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_popularity.setLayoutManager(mLayoutManager);
        recyclerView_popularity.setItemAnimator(new DefaultItemAnimator());
        recyclerView_popularity.setAdapter(moviesPopularityAdapter);

        moviesTopRatedAdapter=new MoviesTopRatedAdapter(this,al_movies_top_rated);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_top_rated.setLayoutManager(mLayoutManager);
        recyclerView_top_rated.setItemAnimator(new DefaultItemAnimator());
        recyclerView_top_rated.setAdapter(moviesTopRatedAdapter);
    }

    @Override
    public void showDialog() {
        super.showDialog();
    }

    @Override
    public void hideDialog() {
        super.hideDialog();
    }

    @Override
    public void showErrorMsg(String msg) {
       super.showErrorMsg(msg);
    }

    @Override
    public void dataUpdateOnDate(List<MoviesResults> al_data) {
        al_movies_date.clear();
        al_movies_date.addAll(al_data);
        moviesDateLimitAdapter.notifyDataSetChanged();
    }

    @Override
    public void dateUpdateOnPopularity(List<MoviesResults> al_data) {
        al_movies_popularity.clear();
        al_movies_popularity.addAll(al_data);
        moviesPopularityAdapter.notifyDataSetChanged();
    }

    @Override
    public void dataUpdateOnTopRated(List<MoviesResults> al_data) {
        al_movies_top_rated.clear();
        al_movies_top_rated.addAll(al_data);
        moviesTopRatedAdapter.notifyDataSetChanged();
    }
}
