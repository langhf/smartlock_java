package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
  *  help 开门请求参数
  * Created by Drelang on 2019/01/18
  */
@Getter
@Setter
public class DoorRequestHelpParam {

    @ApiModelProperty(value = "帮助者姓名", example = "康彦博", required = true, position = 1)
    private String helper;

    @ApiModelProperty(value = "请求帮助的原因", example = "忘记带手机了", required = true, position = 2)
    private String reason;

    @ApiModelProperty(value = "联系方式", example = "13156533607", required = true, position = 3)
    private String contact;

    @ApiModelProperty(value = "用户的 help_token", example = "test_help_token", required = true, position = 4)
    private String helpToken;

    @ApiModelProperty(value = "申请手机的个推cid", example = "xxxxxxxxxxxxxxxxxx", position = 5)
    private String getuiCid;

    @ApiModelProperty(value = "短信验证码", example = "123456", required = true, position = 6)
    private String code;
}
