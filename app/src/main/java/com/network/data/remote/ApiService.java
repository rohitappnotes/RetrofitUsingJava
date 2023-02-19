package com.network.data.remote;

import com.network.data.remote.config.ApiConfiguration;
import com.network.data.remote.model.BaseResponseArrayFormat;
import com.network.data.remote.model.BaseResponseObjectFormat;
import com.network.model.Data;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiConfiguration.EMPLOYEE)
    Call<BaseResponseObjectFormat<Data>> employee();

    @GET(ApiConfiguration.EMPLOYEE_LIST)
    Call<BaseResponseArrayFormat<Data>> employeeList();
}
