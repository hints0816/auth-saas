package org.hints.common.pojo;

import java.io.Serializable;

public class ReturnVo<T> {

    private int code;

    private String msg;

    private T data;


    public ReturnVo() {
    }

    public ReturnVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static ReturnVo success()
    {
        return ReturnVo.success("操作成功");
    }

    public static ReturnVo success(Object data)
    {
        return ReturnVo.success("操作成功", data);
    }

    public static ReturnVo success(String msg)
    {
        return ReturnVo.success(msg, null);
    }

    public static ReturnVo success(String msg, Object data)
    {
        return new ReturnVo(200, msg, data);
    }

    public static ReturnVo error()
    {
        return ReturnVo.error("操作失败");
    }

    public static ReturnVo error(String msg)
    {
        return ReturnVo.error(msg, null);
    }

    public static ReturnVo error(String msg, Object data)
    {
        return new ReturnVo(500, msg, data);
    }

    public static ReturnVo error(int code, String msg)
    {
        return new ReturnVo(code, msg, null);
    }

    public static ReturnVo toAjax(int rows)
    {
        return rows > 0 ? ReturnVo.success() : ReturnVo.error();
    }
}