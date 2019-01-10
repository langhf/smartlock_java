package com.drelang.smartlock.domain;

import lombok.Data;

/**
 * 通用返回对象
 *  Created By Drelang on 2019/1/9
 */
@Data
public class CommonResult {
    public static final int SUCCESS = 200;
    public static final int FAILED = 500;
    private int code;
    private String message;
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
}
