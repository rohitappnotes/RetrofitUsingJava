package com.network.ui.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.network.data.remote.ApiService;
import com.network.data.remote.ApiServiceGenerator;
import com.network.data.remote.ApiUtils;
import com.network.data.remote.model.BaseResponseObjectFormat;
import com.network.databinding.ActivityTestBinding;
import com.network.model.Data;
import com.network.ui.base.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends BaseActivity<ActivityTestBinding> {

    @Override
    protected String getActivityClassName() {
        return TestActivity.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate(Bundle savedInstanceState) {
    }

    @Override
    protected ActivityTestBinding getViewBinding(LayoutInflater inflater) {
        return ActivityTestBinding.inflate(inflater);
    }

    @Override
    protected void init() {
        String hostUrl = ApiUtils.getHost("https://androidwave.com/retrofit-globally-error-handling/");
        mViewBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        ApiService apiService = ApiServiceGenerator.createService(ApiService.class);
        Call<BaseResponseObjectFormat<Data>> call = apiService.employee();
        call.enqueue(new Callback<BaseResponseObjectFormat<Data>>() {
            @Override
            public void onResponse(Call<BaseResponseObjectFormat<Data>> call, Response<BaseResponseObjectFormat<Data>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        BaseResponseObjectFormat<Data> body = response.body();
                        if (body.isSuccess()) {
                            if (body.getData() == null) {
                                Log.e(mTag, "onResponse 1 : " + body.getMessage());
                            } else {
                                Log.e(mTag, "onResponse 2 : " + body.getData().getName());
                            }
                        } else {
                            Log.e(mTag, "onResponse 3 : " + body.isSuccess());
                        }
                    } else {
                        Log.e(mTag, "onResponse 4 : " + response.body().getMessage());
                    }
                } else {
                    Log.e(mTag, "onResponse 5 : " + response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponseObjectFormat<Data>> call, Throwable throwable) {
                Log.e(mTag, "onFailure : " + throwable.getMessage());
            }
        });
    }
}