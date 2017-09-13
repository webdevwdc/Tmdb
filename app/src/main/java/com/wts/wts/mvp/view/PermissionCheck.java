package com.wts.wts.mvp.view;

import android.app.Activity;
import android.support.annotation.NonNull;


public interface PermissionCheck {

     void requestPermissions(Activity mActivity, String permission, int req_code);
     void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, Activity a);


}
