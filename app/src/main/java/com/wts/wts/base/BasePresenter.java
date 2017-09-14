package com.wts.wts.base;

import android.view.View;

import com.wts.wts.mvp.view.BaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {

    @Inject protected V mView;

    protected V getView() {
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    protected boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    protected void handleHTTPException(Throwable e){

        getView().hideDialog();
        if (e instanceof HttpException) {
            try {
                ResponseBody body = ((HttpException) e).response().errorBody();
                JSONObject jObj=new JSONObject(body.string());
                getView().showErrorMsg("Error: "+jObj.optString("status")+" "+jObj.optString("message"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        else if(e instanceof IOException || e instanceof ConnectException){
            getView().showErrorMsg("No Connection available.");
        }
        else if(e instanceof TimeoutException){
            getView().showErrorMsg("Server could not be reached at this moment.");
        }
        else{
            getView().showErrorMsg("Oops! Something went wrong. Try again later.");
        }
    }
}
