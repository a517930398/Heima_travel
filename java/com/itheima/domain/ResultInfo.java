package com.itheima.domain;


public class ResultInfo {
    //自定义状态码
    private int code;
    //成功/失败标记
    private boolean flag;
    //异常信息
    private String errorMsg;
    //响应数据
    private Object data;

    //无参构造
    public ResultInfo() {
    }

    //失败状态：状态码
    public ResultInfo(int code, boolean flag, String errorMsg, Object data) {
        this.code = code;
        this.flag = flag;
        this.errorMsg = errorMsg;
        this.data = data;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
