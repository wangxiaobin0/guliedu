package com.common.utils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应结果
 * @author
 * @date 2020/5/1
 */
@Data
public class R {
    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();
    private R(){}

    /**
     * 交易成功
     * @return
     */
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResponseCode.SUCCESS);
        r.setMessage("交易成功");
        return r;
    }

    /**
     * 交易失败
     * @return
     */
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResponseCode.ERROR);
        r.setMessage("交易失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
    /**
     * 设置交易码
     * @param code
     * @return
     */
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置交易信息
     * @param message
     * @return
     */
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 设置交易数据
     * @param key
     * @param value
     * @return
     */
    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
