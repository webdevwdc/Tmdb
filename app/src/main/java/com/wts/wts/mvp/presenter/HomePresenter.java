package com.wts.wts.mvp.presenter;

import android.util.Log;

import com.wts.wts.api.RestApiService;
import com.wts.wts.base.BasePresenter;
import com.wts.wts.mvp.model.home.MoviesDateRequest;
import com.wts.wts.mvp.view.HomeView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by android on 13/9/17.
 */

public class HomePresenter extends BasePresenter<HomeView> implements Observer<MoviesDateRequest> {

    String req_type="";

    @Inject
    RestApiService mApiService;

    @Inject
    public HomePresenter() {
    }

    public void getMoviesByDate(){
        req_type="by_date";
        getView().showDialog();
        Observable<MoviesDateRequest> moviesResponseObservable = mApiService.getMoviesByDate();
        subscribe(moviesResponseObservable,this);
    }

    public void getMoviesByPopularity(){
        req_type="by_popularity";
        getView().showDialog();
        Observable<MoviesDateRequest> moviesResponseObservable = mApiService.getMoviesByPopularity();
        subscribe(moviesResponseObservable,this);
    }

    private void getMoviesByTopRated() {
        req_type="by_top_rated";
        getView().showDialog();
        Observable<MoviesDateRequest> moviesResponseObservable = mApiService.getMoviesByTopRated();
        subscribe(moviesResponseObservable,this);
    }

    @Override
    public void onCompleted() {
        getView().hideDialog();
        Log.d("Response-Completed","onCompleted");
    }

    @Override
    public void onError(Throwable e) {
       handleHTTPException(e);
    }

    @Override
    public void onNext(MoviesDateRequest moviesDateRequest) {
        Log.d("Response-Next","onNext");
        if(req_type.equalsIgnoreCase("by_date")) {
            getView().dataUpdateOnDate(moviesDateRequest.getResults());
            getMoviesByPopularity();
        }
        else if(req_type.equalsIgnoreCase("by_popularity")) {
            getView().dateUpdateOnPopularity(moviesDateRequest.getResults());
            getMoviesByTopRated();
        }
        else if(req_type.equalsIgnoreCase("by_top_rated"))
            getView().dataUpdateOnTopRated(moviesDateRequest.getResults());
    }

}
