package com.network.data.remote.config;

import com.network.AppConstants;

public class ApiConfiguration {

    /*
     * Note :  We can set timeouts settings on the underlying HTTP client.
     * If we don’t specify a client, Retrofit will create one with default connect and read timeouts.
     * By default, Retrofit uses the following timeouts :
     *                                                      Connection timeout: 10 seconds
     *                                                      Read timeout: 10 seconds
     *                                                      Write timeout: 10 seconds
     */
    public static final int CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS              = 60; /* 60 seconds */
    public static final int CUSTOM_HTTP_WRITE_TIMEOUT_IN_SECONDS                = 30; /* 30 seconds */
    public static final int CUSTOM_HTTP_READ_TIMEOUT_IN_SECONDS                 = 15; /* 15 seconds */

    public static final String CUSTOM_OK_HTTP_CACHE_DIR_NAME                   = "customOkHttpClientCache";
    public static final long CUSTOM_OK_HTTP_CACHE_SIZE                          = 10 * 1024 * 1024; /* 10 MB Cache size */

    public static final int CUSTOM_CACHE_DURATION_WITH_NETWORK_IN_SECONDS       = 10;
    public static final int CUSTOM_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS    = 14 * 24 * 60 * 60; /* Expired in two week. */

    public static final String BASE_URL                     =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.BASE_URL : AppConstants.ApiInfo.Production.BASE_URL;
    public static final String API_KEY                      =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.API_KEY : AppConstants.ApiInfo.Production.API_KEY;
    public static final String BEARER_AUTHENTICATION_TOKEN  =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.BEARER_AUTHENTICATION_TOKEN : AppConstants.ApiInfo.Production.BEARER_AUTHENTICATION_TOKEN;

    /*
     * End points
     */
    public static final String EMPLOYEE                     = "Json/employee.json";
    public static final String EMPLOYEE_LIST                = "Json/employeeList.json";

    public static final String UPLOAD                       = "UploadFile/upload_multipart_file.php";

    public static final String SIGN_UP                      = "RestApi/middleware/AppSignUp.php";
    public static final String SIGN_IN                      = "RestApi/middleware/SignIn.php";
    public static final String SOCIAL_SIGN_IN               = "RestApi/middleware/SocialSignIn.php";
    public static final String GET_USERS                    = "RestApi/middleware/GetUsers.php";
}
