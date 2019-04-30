package com.yb.api.center.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author yebing
 */
public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = -8964317548670781085L;
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "OK";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_MESSAGE = "内部异常";
    public static final int ILLEGAL_ARGUMENT_CODE_ = 400;
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
    private int code;
    private String message;
    private T result;

    public static int getSuccessCode() {
        return 200;
    }

    public static String getSuccessMessage() {
        return "OK";
    }

    public static int getErrorCode() {
        return 500;
    }

    public static String getErrorMessage() {
        return "内部异常";
    }

    public static int getIllegalArgumentCode() {
        return 400;
    }

    public static String getIllegalArgumentMessage() {
        return "参数非法";
    }

    public Wrapper() {
        this(200, "OK");
    }

    public Wrapper(int code, String message) {
        this.code(code).message(message);
    }

    public Wrapper(int code, String message, T result) {
        this.code(code).message(message).result(result);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    public Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return 200 == this.code;
    }
}
