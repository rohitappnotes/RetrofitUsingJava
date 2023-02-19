package com.network.data.remote;

import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.network.BaseApplication;
import com.network.BuildConfig;
import com.network.data.remote.cache.ProvideCacheInterceptor;
import com.network.data.remote.cache.ProvideOfflineCacheInterceptor;
import com.network.data.remote.config.ApiConfiguration;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.util.Log.VERBOSE;
import android.content.Context;
import android.util.Log;

public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (mRetrofit == null) {
            synchronized (ApiClient.class) {
                mRetrofit = getRetrofitInstance(baseUrl);
            }
        }
        return mRetrofit;
    }

    private static Retrofit getRetrofitInstance(String baseUrl) {
        OkHttpClient.Builder okHttpClientBuilder = getOkHttpClientBuilderInstance(BaseApplication.getApplication());
        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofitInstance = builder.build();
        return retrofitInstance;
    }

    private static OkHttpClient.Builder getOkHttpClientBuilderInstance(Context context) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.connectTimeout(ApiConfiguration.CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(ApiConfiguration.CUSTOM_HTTP_WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(ApiConfiguration.CUSTOM_HTTP_READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        okHttpClientBuilder.retryOnConnectionFailure(true);

        Cache cache = new Cache(context.getCacheDir(), ApiConfiguration.CUSTOM_OK_HTTP_CACHE_SIZE);
        okHttpClientBuilder.cache(cache);
        okHttpClientBuilder.addInterceptor(new ProvideOfflineCacheInterceptor(context)); // used if network off OR on
        okHttpClientBuilder.addNetworkInterceptor(new ProvideCacheInterceptor(context)); // only used when network is on

        /* okHttpClientBuilder.addInterceptor(new ProvideOfflineCacheInterceptor(context, ApiConfiguration.CUSTOM_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS));
        okHttpClientBuilder.addNetworkInterceptor(new ProvideCacheInterceptor(context, ApiConfiguration.CUSTOM_CACHE_DURATION_WITH_NETWORK_IN_SECONDS));
        okHttpClientBuilder.cache(provideOkHttpCache(context));*/

        /* we must avoid sharing sensitive data into logcat if we aren't in Debug mode */
        if (BuildConfig.DEBUG) {
            Interceptor interceptor = new LoggingInterceptor.Builder().setLevel(Level.BASIC).log(VERBOSE).build();
            okHttpClientBuilder.addInterceptor(interceptor);
        }
        return okHttpClientBuilder;
    }

    private static Cache provideOkHttpCache(Context context) {
        Cache cache = null;
        try {
            File httpCacheDirectory = new File(context.getCacheDir(), ApiConfiguration.CUSTOM_OK_HTTP_CACHE_DIR_NAME);
            cache = new Cache(httpCacheDirectory, ApiConfiguration.CUSTOM_OK_HTTP_CACHE_SIZE);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Cache!", e);
        }
        return cache;
    }
}
