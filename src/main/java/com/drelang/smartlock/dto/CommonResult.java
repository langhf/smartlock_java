package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用返回对象
 *  Created By Drelang on 2019/1/9
 */
@Data
public class CommonResult {
    // 操作成功
    public static final int SUCCESS = 200;
    // 未认证
    public static final int UNAUTHORIZED = 401;
    // 未授权
    public static final int FORBIDDEN = 403;
    // 操作失败
    public static final int FAILED = 500;

    @ApiModelProperty(value = "http 状态码", example = "200")
    private int code;
    @ApiModelProperty(value = "提示消息", example = "操作成功")
    private String message;
    @ApiModelProperty(value = "返回的数据, Object 类型")
    private Object data;

    /**
     *  普通成功返回
     * @param data 获取的数据
     * @return CommonResult
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    /**
     *  普通成功返回
     * @param message 状态码含义
     * @param data 返回的数据
     * @return CommonResult
     */
    public CommonResult success(String message, Object data) {
        this.code = SUCCESS;
        this.message = message;
        this.data = data;
        return this;
    }

    /**
     *  普通失败提示信息
     * @return CommonResult
     */
    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }

    /**
     *  普通失败提示信息
     * @param message 状态码含义
     * @return CommonResult
     */
    public CommonResult failed(String message) {
        this.code = FAILED;
        this.message = message;
        return this;
    }

    /**
     *  未授权返回消息
     * @param message  提示消息
     * @return CommonResult
     */
    public CommonResult unauthorized(String message) {
        this.code = UNAUTHORIZED;
        this.message = "暂未登录或token已过期";
        this.data = message;
        return this;
    }

    /**
     *  未授权返回信息
     * @param message 提示消息
     * @return CommonResult
     */
    public CommonResult forbidden(String message) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        this.data = message;
        return this;
    }
}
