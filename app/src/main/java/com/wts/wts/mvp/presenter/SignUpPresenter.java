package com.wts.wts.mvp.presenter;

import com.wts.wts.api.RestApiService;
import com.wts.wts.base.BasePresenter;
import com.wts.wts.modules.signup.model.SignUpRequest;
import com.wts.wts.mvp.view.SignUpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Observable;
import rx.Observer;

/**
 * Created by android on 13/9/17.
 */

public class SignUpPresenter extends BasePresenter<SignUpView> implements Observer<SignUpRequest> {

    @Inject
    RestApiService mApiService;

    @Inject
    public SignUpPresenter() {
    }

    public void onFormSubmit(String firstName,String lastName,String pwd,String email,String contactNo){
     if(validateForm(firstName,lastName,pwd,email,contactNo)){
         getView().showDialog();

         Observable<SignUpRequest> loginResponseObservable = mApiService.registerUser(firstName,lastName,pwd,email,contactNo);
         subscribe(loginResponseObservable,this);
     }
    }

    @Override
    public void onCompleted() {
        getView().hideDialog();
    }

    @Override
    public void onError(Throwable e) {
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
        }else{
            getView().showErrorMsg("Oops! Some went wrong. Try again later.");
        }
    }

    @Override
    public void onNext(SignUpRequest signUpRequest) {
            getView().navigateToHome(signUpRequest.getMessage());
    }

    private boolean validateForm(String firstName,String lastName,String pwd,String email,String contactNo){

        if(firstName==null || firstName.trim().length()==0) {
            getView().showErrorMsg("First Name cannot be blank.");
            return false;
        }
        else if(lastName!=null && lastName.length()==0){
            getView().showErrorMsg("Last Name cannot be blank.");
            return false;
        }
        else if(pwd!=null && pwd.length()==0){
            getView().showErrorMsg("Password cannot be blank.");
            return false;
        }
        else if(email==null || email.trim().length()==0) {
            getView().showErrorMsg("Email cannot be blank.");
            return false;
        }
        else if(!isValidEmail(email)){
            getView().showErrorMsg("Provide valid Email address.");
            return false;
        }
        else if(contactNo==null || contactNo.trim().length()==0) {
            getView().showErrorMsg("Contact No cannot be blank.");
            return false;
        }

        return true;
    }
}
