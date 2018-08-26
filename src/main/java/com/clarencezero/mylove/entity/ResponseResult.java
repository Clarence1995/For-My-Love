package com.clarencezero.mylove.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult {
    private int code;  // 状态码
    private String msg;
    private Object data;

    public ResponseResult success(String msg, Object data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(200);
        responseResult.setMsg(msg);
        responseResult.setData(data);
        return responseResult;
    }

    public ResponseResult fail(String msg, Object data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(0);
        responseResult.setMsg(msg);
        responseResult.setData(data);
        return responseResult;
    }
}
