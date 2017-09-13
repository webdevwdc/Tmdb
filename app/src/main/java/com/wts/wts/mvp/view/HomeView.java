package com.wts.wts.mvp.view;

import com.wts.wts.mvp.model.home.MoviesResults;

import java.util.List;


public interface HomeView extends BaseView{

    void dataUpdateOnDate(List<MoviesResults> al_data);
    void dateUpdateOnPopularity(List<MoviesResults> al_data);
    void dataUpdateOnTopRated(List<MoviesResults> al_data);
}
