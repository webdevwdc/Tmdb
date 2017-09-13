package com.wts.wts.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wts.wts.R;
import com.wts.wts.base.BaseActivity;
import com.wts.wts.di.component.DaggerLoginComponent;
import com.wts.wts.di.modules.LoginModule;
import com.wts.wts.modules.home.HomeActivity;
import com.wts.wts.modules.signup.SignUpActivity;
import com.wts.wts.mvp.presenter.LoginPresenter;
import com.wts.wts.mvp.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter mLoginPresenter;

    @Bind(R.id.edt_email)EditText edt_email;
    @Bind(R.id.edt_pwd)EditText edt_pwd;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        injectDependency();
    }

    //dagger2 dependency
    private void injectDependency(){
        DaggerLoginComponent.builder().applicationComponent(getApplicationCOmponent())
                .loginModule(new LoginModule(this)).build().inject(this);
    }

    //inflating layout
    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_signup,R.id.btn_signUp})
    public void onClick(View v){

        switch (v.getId()){

            case R.id.btn_signup:

                mLoginPresenter.onLoginSubmit(edt_email.getText().toString(),
                        edt_pwd.getText().toString());

                break;

            case R.id.btn_signUp:

                Intent intent=new Intent(this, SignUpActivity.class);
                startActivity(intent);
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
    public void NavigateToHome(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
