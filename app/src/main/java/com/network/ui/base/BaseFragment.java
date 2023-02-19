package com.network.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected String mTag;
    protected VB mViewBinding;
    protected View rootView;

    protected abstract String getFragmentClassName();
    protected abstract void doInOnCreate(Bundle savedInstanceState);
    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);
    protected abstract void init();

    private void initTag() {
        mTag = getFragmentClassName();
        if (mTag.length() > 23) {
            mTag = mTag.substring(0, 22);
        }
    }

    private void initViewBinding(LayoutInflater inflater, ViewGroup container) {
        mViewBinding = getViewBinding(inflater, container);
        rootView = mViewBinding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTag();
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");

        doInOnCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(mTag, "onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)");
        initViewBinding(inflater, container);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(mTag, "onViewCreated(@NonNull View view, Bundle savedInstanceState)");

        init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(mTag, "onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(mTag, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }

    @Override
    public void onDetach() {
        if (null != mViewBinding) {
            mViewBinding = null;
        }
        if (null != rootView) {
            rootView = null;
        }
        super.onDetach();
        Log.i(mTag, "onDetach()");
    }
}
