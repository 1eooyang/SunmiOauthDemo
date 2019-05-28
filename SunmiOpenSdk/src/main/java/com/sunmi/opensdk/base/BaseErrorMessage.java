package com.sunmi.opensdk.base;

/**
 * 作者：杨柳 on 2019/5/13 0013 11:47
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class BaseErrorMessage {
    private String errorMessage = "not install usercenter client!!!!!";
    private String errorCode = "8000";

    public BaseErrorMessage() {
    }

    public BaseErrorMessage(String message, String code) {
        this.errorMessage = message;
        this.errorCode = code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
