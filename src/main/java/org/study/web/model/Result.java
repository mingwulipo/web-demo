package org.study.web.model;

/**
 * @author lipo
 * @version v1.0
 * @date 2020-01-10 14:29
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public static Result ok() {
        return new Result<>(0, "success");
    }

    public static Result error0() {
        return new Result<>(500, "服务器繁忙");
    }

    public static Result error(String msg) {
        return new Result<>(500, msg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
