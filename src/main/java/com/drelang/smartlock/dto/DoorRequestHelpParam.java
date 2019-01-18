package com.drelang.smartlock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorRequestHelpParam {

    /**
     * 帮助者姓名
     */
    private String helper;

    /**
     * 请求帮助的原因
     */
    private String reason;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 用户的 help_token
     */
    private String helpToken;

    /**
     * 申请手机的个推cid
     */
    private String getuiCid;

    /**
     * 短信验证码
     */
    private String code;
}
