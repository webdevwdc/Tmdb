package com.wts.wts.mvp.presenter;

import android.util.Log;

import com.wts.wts.api.RestApiService;
import com.wts.wts.base.BasePresenter;
import com.wts.wts.mvp.model.home.MoviesDateRequest;
import com.wts.wts.mvp.model.login.LoginRequest;
import com.wts.wts.mvp.view.LoginView;

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

/**
 * Created by webskitters on 12/9/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements Observer<LoginRequest> {

    @Inject
    RestApiService mApiService;

    @Inject
    public LoginPresenter() {
    }

    public void onLoginSubmit(String email,String pwd){
        if(validateForm(email,pwd)){
            getView().showDialog();
            Observable<LoginRequest> loginResponseObservable = mApiService.login(email,pwd);
            subscribe(loginResponseObservable,this);
        }
    }

    private boolean validateForm(String email, String pwd){
        if(email==null || email.trim().length()==0) {
            getView().showErrorMsg("Email cannot be blank");
            return false;
        }
        else if(pwd!=null && pwd.length()==0){
            getView().showErrorMsg("Password cannot be blank");
            return false;
        }
        return true;
    }

    @Override
    public void onCompleted() {
        getView().hideDialog();
    }

    @Override
    public void onError(Throwable e) {
        handleHTTPException(e);
    }

    @Override
    public void onNext(LoginRequest loginRequest) {
        Log.d("Login-Response",loginRequest.getMessage());
        getView().NavigateToHome(loginRequest.getMessage());
    }
}
