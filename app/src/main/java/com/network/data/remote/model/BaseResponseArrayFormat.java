package com.network.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/*

Api Response Format

{
	"code": 200,
	"success": true,
	"message": "Data found",
	"data": [{
		"id": "1",
		"name": "Rohit",
		"job": "Android Developer",
		"salary": "25000",
		"profile_picture": "https://backend24.000webhostapp.com/Json/profile.jpg"
	}, {
		"id": "2",
		"name": "Kiyansh",
		"job": "iOS Developer",
		"salary": "15000",
		"profile_picture": "https://backend24.000webhostapp.com/Json/profile.jpg"
	}]
}
*/
public class BaseResponseArrayFormat<T> {

    /**
     * Http Status Codes
     */
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * success is true if
     * Login successfully
     * Your account has been created successfully
     * You have successfully registered
     * Verification code send successfully
     * data found (example list) etc.,
     * success is false if
     * Invalid credentials. Login failed,
     * Sorry, Email is already exists, use another Email,
     * Sorry, Phone number is already exists, use another Phone number,
     * You have entered an incorrect verification code,
     * data not found (example list) etc.,
     */
    @SerializedName("success")
    @Expose
    private boolean success;

    /**
     * message
     * Login successfully
     * Your account has been created successfully
     * You have successfully registered
     * Verification code send successfully
     * data found (example list),
     * Invalid credentials. Login failed,
     * Sorry, Email is already exists, use another Email,
     * Sorry, Phone number is already exists, use another Phone number,
     * You have entered an incorrect verification code,
     * data not found (example list) etc.,
     */
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<T> data;

    public BaseResponseArrayFormat(int code, boolean success, String message, ArrayList<T> data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
