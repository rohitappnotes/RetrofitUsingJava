package com.network.data.remote;

import com.network.data.remote.config.ApiConfiguration;

public class ApiServiceGenerator {

    /**
     * Retrofit Service Generator class which initializes the calling ApiService
     *
     * @param serviceClass -  The Retrofit Service Interface class.
     */
    public static <S> S createService(Class<S> serviceClass) {
        return ApiClient.getClient(ApiConfiguration.BASE_URL).create(serviceClass);
    }
}
