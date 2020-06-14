package com.ibm.w3.utils;

public class CommonResult {

    private Integer status;

    private String msg;

    private Object data;
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CommonResult() {

    }
    
    public CommonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public CommonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public static CommonResult ok() {
        return new CommonResult(null);
    }
    
    public static CommonResult ok(Object data) {
        return new CommonResult(data);
    }
    
    public static CommonResult build(Integer status, String msg, Object data) {
        return new CommonResult(status, msg, data);
    }
    
    public static CommonResult build(Integer status, String msg) {
        return new CommonResult(status, msg, null);
    }
}
