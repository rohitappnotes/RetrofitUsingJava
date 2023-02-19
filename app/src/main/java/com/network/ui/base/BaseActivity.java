package com.network.ui.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected String mTag;
    protected VB mViewBinding;
    protected View rootView;

    protected abstract String getActivityClassName();
    protected abstract void doInOnCreate(Bundle savedInstanceState);
    protected abstract VB getViewBinding(LayoutInflater inflater);
    protected abstract void init();

    private void initTag() {
        mTag = getActivityClassName();
        if (mTag.length() > 23) {
            mTag = mTag.substring(0, 22); // first 22 chars
        }
    }

    private void initViewBinding(LayoutInflater inflater) {
        mViewBinding = getViewBinding(inflater);
        rootView = mViewBinding.getRoot();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTag();
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");

        doInOnCreate(savedInstanceState);

        initViewBinding(getLayoutInflater());
        setContentView(rootView);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    protected void onRestart() { /* Only called after onStop() */
        super.onRestart();
        Log.i(mTag, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    protected void onDestroy() {
        if (null != mViewBinding) {
            mViewBinding = null;
        }
        if (null != rootView) {
            rootView = null;
        }
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }
}
