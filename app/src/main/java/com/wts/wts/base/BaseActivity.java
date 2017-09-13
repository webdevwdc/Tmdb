package com.wts.wts.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wts.wts.R;
import com.wts.wts.app.TrackApplication;
import com.wts.wts.di.component.ApplicationComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }


    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    protected void resolveDaggerDependency() {}

    protected void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage("Loading..");
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showErrorMsg(String message){
        displayDialog(message);
    }

    protected ApplicationComponent getApplicationCOmponent(){
        return ((TrackApplication)getApplication()).getApplicationComponent();
    }

    protected abstract int getContentView();

    public void displayDialog(String msg) {

        final Dialog dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.dialog_success);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(false);

        final TextView btn_yes = (TextView) dialog.findViewById(R.id.btn_yes);
        final TextView btn_no =  (TextView) dialog.findViewById(R.id.btn_no);
        final TextView txt_msg = (TextView) dialog.findViewById(R.id.txt_msg);
        txt_msg.setText(msg);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
