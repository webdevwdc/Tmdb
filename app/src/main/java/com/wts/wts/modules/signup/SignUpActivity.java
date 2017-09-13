package com.wts.wts.modules.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wts.wts.R;
import com.wts.wts.base.BaseActivity;
import com.wts.wts.di.component.DaggerSignUpComponent;
import com.wts.wts.di.modules.SignUpModule;
import com.wts.wts.modules.home.HomeActivity;
import com.wts.wts.mvp.presenter.SignUpPresenter;
import com.wts.wts.mvp.view.SignUpView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUpView {

    @Bind(R.id.edt_firstName) EditText edt_firstName;
    @Bind(R.id.edt_lastName) EditText edt_lastName;
    @Bind(R.id.edt_password) EditText edt_password;
    @Bind(R.id.edt_email) EditText edt_email;
    @Bind(R.id.edt_contactNo) EditText edt_contactNo;

    @Inject
    SignUpPresenter mSignUpPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        injectDependency();
    }

    private void injectDependency(){
        DaggerSignUpComponent.builder().applicationComponent(getApplicationCOmponent()).signUpModule(
                new SignUpModule(this)).build().inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.btn_signup)
    public void onClick(View v){

        switch (v.getId()){

            case R.id.btn_signup:
                mSignUpPresenter.onFormSubmit(edt_firstName.getText().toString(),
                        edt_lastName.getText().toString(),edt_password.getText().toString(),
                        edt_email.getText().toString(),edt_contactNo.getText().toString());
                break;
        }
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
    public void navigateToHome(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
