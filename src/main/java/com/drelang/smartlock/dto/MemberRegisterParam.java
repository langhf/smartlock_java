package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


/**
  *  用户注册参数
  * Created by Drelang on 2019/01/12
  */
@Getter
@Setter
public class MemberRegisterParam {

    @ApiModelProperty(value = "用户名", example = "testUser", required = true, position = 1)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", example = "testPassword", required = true, position = 2)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号", example = "testTelephone", required = true, position = 3)
    @NotEmpty(message = "手机号不能为空")
    private String telephone;

}
