package com.network.data.remote;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ApiUtils {

    public static final String mTag = ApiUtils.class.getSimpleName();

    private ApiUtils() {
        throw new UnsupportedOperationException("You can't create instance of Util class. Please use as static..");
    }

    /**
     * Get the host
     *
     * @param path : https://androidwave.com/retrofit-globally-error-handling/
     * @return : http://androidwave.com
     */
    public static String getHost(String path) {
        String host = null;
        String key = null;
        try {
            URL url = new URL(path);
            host = url.getHost();
            if (!TextUtils.isEmpty(host)) {
                String[] splits = path.split(host);
                if (splits != null && splits.length == 2) {
                    key = splits[1];
                }
                host = "http://" + host;
                if (key.startsWith(":")) {
                    int index = key.indexOf("/");
                    String port = key.substring(0, index);
                    host += port;
                    Log.e(mTag, "The url starts with: port =" + port);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.e(mTag, "host = " + host);
        return host;
    }

    /**
     * Get the url
     *
     * @param path : https://androidwave.com/retrofit-globally-error-handling/
     * @return : /retrofit-globally-error-handling/
     */
    public static String getUrl(String path) {
        String key = null;
        try {
            URL url = new URL(path);
            String host = url.getHost();
            if (!TextUtils.isEmpty(host)) {
                String[] splits = path.split(host);
                if (splits != null && splits.length == 2) {
                    key = splits[1];
                }
                if (key.startsWith(":")) {
                    Log.e(mTag, "The url starts with:");
                    int index = key.indexOf("/");
                    key = key.substring(index);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.e(mTag, "key = " + key);
        return key;
    }

    /**
     * Declare upload file type
     *
     * @return MultipartBody
     */
    public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
            builder.addFormDataPart("uploadfile" + i, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * Declare upload file type
     *
     * @return MultipartBody.Part
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("uploadfile" + i, file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
